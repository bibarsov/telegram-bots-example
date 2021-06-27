package ru.bibarsov.telegram.bots.example.service.handler;

import ru.bibarsov.telegram.bots.client.service.Command;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public enum CommandType implements Command<CommandType> {

    START("start"),
    PING("ping");

    private final String value;

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public CommandType getSelf() {
        return this;
    }

    CommandType(String value) {
        this.value = value;
    }
}
