package request.logic.workers;

import listen.providers.CollectionActionsListener;
import models.handlers.RoutesHandler;
import request.logic.CallerBack;
import request.logic.requests.ServerRequest;
import response.logic.senders.ResponseSender;
import responses.CollectionUpdatedResponse;

import java.util.ArrayList;

public class ListenCollectionChangeHubWorker implements RequestWorker {
    private static final ArrayList<ServerRequest> callerBacks = new ArrayList<>();

    static {
        RoutesHandler.getInstance().addCollectionListener(new CollectionActionsListener());
    }

    public static void removeCallerBack(CallerBack callerBack) {
        callerBacks.removeIf(x -> x.getFrom().equals(callerBack));
    }

    public static void sendToAllCallers(CollectionUpdatedResponse response) {
        callerBacks.forEach(x -> ResponseSender.sendResponse(response, x.getConnection(), x.getFrom()));
    }

    @Override
    public void workWithRequest(ServerRequest request) {
        callerBacks.add(request);
    }
}
