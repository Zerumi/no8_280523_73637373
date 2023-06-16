package response.logic;

import response.CommandStatusResponse;

import java.util.ResourceBundle;

public record StatusResponse(String response, int code, Object... args) {

    public CommandStatusResponse toCommandResponse() {
        return new CommandStatusResponse(response, code);
    }

    public CommandStatusResponse toLocalizedCommandResponse(ResourceBundle bundleWithStr) {
        return new CommandStatusResponse(bundleWithStr.getString(response), code);
    }
}
