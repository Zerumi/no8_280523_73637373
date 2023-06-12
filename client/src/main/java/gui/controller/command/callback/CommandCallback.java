package gui.controller.command.callback;

import responses.CommandStatusResponse;

public interface CommandCallback {
    void writeResponse(CommandStatusResponse response);
}
