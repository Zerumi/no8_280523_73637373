package gui.controller.command.callback;

import response.CommandStatusResponse;

public interface CommandCallback {
    void writeResponse(CommandStatusResponse response);
}
