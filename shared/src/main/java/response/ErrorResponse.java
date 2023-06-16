package response;

public class ErrorResponse extends BaseResponse {

    private final String shortMsg;
    private final String msg;

    public ErrorResponse(String shortMsg, String msg) {
        this.shortMsg = shortMsg;
        this.msg = msg;
    }

    public String getShortMsg() {
        return shortMsg;
    }

    public String getMsg() {
        return msg;
    }
}
