package gui.controllers.command;

import responses.CommandStatusResponse;

public interface CommandCallback {
    void writeResponse(CommandStatusResponse response);
}
