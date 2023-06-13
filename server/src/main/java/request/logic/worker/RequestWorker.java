package request.logic.worker;

import request.logic.request.ServerRequest;

public interface RequestWorker {
    void workWithRequest(ServerRequest request);
}
