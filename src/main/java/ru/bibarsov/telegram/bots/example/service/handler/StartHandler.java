package ru.bibarsov.telegram.bots.example.service.handler;

import ru.bibarsov.telegram.bots.client.dto.Chat;
import ru.bibarsov.telegram.bots.client.dto.Message;
import ru.bibarsov.telegram.bots.client.service.MessageService;
import ru.bibarsov.telegram.bots.client.service.handler.Handler;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class StartHandler extends Handler<Command> {

    private final MessageService messageService;

    public StartHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void handlePrivateMessage(Message message) {
        Chat chat = message.chat;
        long userId = chat.id;
        messageService.scheduleMessage(userId, "Hello");
    }

    @Override
    public Command getCommand() {
        return Command.START;
    }
}
