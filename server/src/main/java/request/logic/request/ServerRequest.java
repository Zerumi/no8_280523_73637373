package request.logic.request;

import org.checkerframework.checker.nullness.qual.NonNull;
import request.logic.CallerBack;
import request.BaseRequest;
import server.logic.abstrct.ServerConnection;

public class ServerRequest {
    private final BaseRequest request;
    private final CallerBack from;
    private final ServerConnection connection;

    public ServerRequest(@NonNull BaseRequest request, @NonNull CallerBack from, @NonNull ServerConnection connection) {
        this.request = request;
        this.from = from;
        this.connection = connection;
    }

    public BaseRequest getUserRequest() {
        return request;
    }

    public ServerConnection getConnection() {
        return connection;
    }

    public CallerBack getFrom() {
        return from;
    }
}
