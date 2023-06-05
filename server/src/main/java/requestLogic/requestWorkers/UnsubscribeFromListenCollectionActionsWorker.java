package requestLogic.requestWorkers;

import requestLogic.requests.ServerRequest;

public class UnsubscribeFromListenCollectionActionsWorker implements RequestWorker {
    @Override
    public void workWithRequest(ServerRequest request) {
        ListenCollectionChangeHubWorker.removeCallerBack(request.getFrom());
    }
}
