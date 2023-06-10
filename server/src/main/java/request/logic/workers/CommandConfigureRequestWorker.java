package request.logic.workers;

import command.logic.CommandDescription;
import command.manager.CommandExporter;
import request.logic.requests.ServerRequest;
import response.logic.senders.ResponseSender;
import responses.CommandDescriptionsResponse;

import java.util.ArrayList;

public class CommandConfigureRequestWorker implements RequestWorker {
    @Override
    public void workWithRequest(ServerRequest request) {
        ArrayList<CommandDescription> commands = CommandExporter.getCommandsToExport();
        CommandDescriptionsResponse response = new CommandDescriptionsResponse(commands);
        ResponseSender.sendResponse(response, request.getConnection(), request.getFrom());
    }
}
