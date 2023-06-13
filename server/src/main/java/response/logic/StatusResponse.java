package response.logic;

import response.CommandStatusResponse;

public record StatusResponse(String response, int code) {

    public CommandStatusResponse toCommandResponse() {
        return new CommandStatusResponse(response, code);
    }
}
