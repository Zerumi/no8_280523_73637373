package request.logic.worker;

import command.logic.CommandDescription;
import command.manager.CommandExporter;
import request.logic.request.ServerRequest;
import response.logic.sender.ResponseSender;
import response.CommandDescriptionsResponse;

import java.util.ArrayList;

public class CommandConfigureRequestWorker implements RequestWorker {
    @Override
    public void workWithRequest(ServerRequest request) {
        ArrayList<CommandDescription> commands = CommandExporter.getCommandsToExport();
        CommandDescriptionsResponse response = new CommandDescriptionsResponse(commands);
        ResponseSender.sendResponse(response, request.getConnection(), request.getFrom());
    }
}
