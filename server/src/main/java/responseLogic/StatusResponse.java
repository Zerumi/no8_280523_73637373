package responseLogic;

import responses.CommandStatusResponse;

public class StatusResponse {
    private final int code;
    private final String response;

    public StatusResponse(String response, int code) {
        this.response = response;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getResponse() {
        return response;
    }

    public CommandStatusResponse toCommandResponse() {
        return new CommandStatusResponse(response, code);
    }
}
