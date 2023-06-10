package request.logic.workers;

import request.logic.requests.ServerRequest;

public interface RequestWorker {
    void workWithRequest(ServerRequest request);
}
