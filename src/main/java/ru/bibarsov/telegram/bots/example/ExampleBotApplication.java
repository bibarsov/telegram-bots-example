package ru.bibarsov.telegram.bots.example;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import ru.bibarsov.telegram.bots.client.repository.client.TelegramBotApi;
import ru.bibarsov.telegram.bots.client.serialization.JsonHelper;
import ru.bibarsov.telegram.bots.client.service.MessageService;
import ru.bibarsov.telegram.bots.client.service.UpdatePollerService;
import ru.bibarsov.telegram.bots.client.service.handler.CommandHandler;
import ru.bibarsov.telegram.bots.common.util.PropertiesReader;
import ru.bibarsov.telegram.bots.example.service.handler.Command;
import ru.bibarsov.telegram.bots.example.service.handler.PingHandler;
import ru.bibarsov.telegram.bots.example.service.handler.StartHandler;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Properties;

import static com.google.common.base.Preconditions.checkNotNull;

@ParametersAreNonnullByDefault
public class ExampleBotApplication {

    private static final String EXAMPLEBOT_PROPERTIES_FILE_NAME = "examplebot.properties";

    public static void main(String[] args) {
        String propertyFileName = EXAMPLEBOT_PROPERTIES_FILE_NAME;
        if (args.length > 0) {
            propertyFileName = args[0];
        }

        //loggers
        Logger rootLogger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        rootLogger.setLevel(Level.INFO);

        Logger logger = (Logger) LoggerFactory.getLogger(UpdatePollerService.class);
        logger.setLevel(Level.INFO);

        //load properties
        Properties prop = PropertiesReader.getPropertiesFromFileOrSystemResource(propertyFileName);
        String botApiKey = checkNotNull(prop.getProperty("common.bot-api-key"));
        int workersThreadCount = Integer.parseInt(checkNotNull(prop.getProperty("single-handler-dispatcher.thread-count")));

        JsonHelper jsonHelper = new JsonHelper();

        TelegramBotApi telegramBotApi = new TelegramBotApi(
            jsonHelper,
            botApiKey
        );
        MessageService messageService = new MessageService(telegramBotApi);

        //handlers
        CommandHandler<Command> startHandler = new StartHandler(messageService);
        CommandHandler<Command> pingHandler = new PingHandler(messageService);

        UpdatePollerService pollerService = new UpdatePollerService(
            botApiKey,
            workersThreadCount,
            List.of(startHandler, pingHandler),
            startHandler,
            Command.class
        );
        pollerService.doJob();
    }
}
