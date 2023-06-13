package request.logic.worker;

import request.logic.request.ServerRequest;

public class UnsubscribeFromListenCollectionActionsWorker implements RequestWorker {
    @Override
    public void workWithRequest(ServerRequest request) {
        ListenCollectionChangeHubWorker.removeCallerBack(request.getFrom());
    }
}
