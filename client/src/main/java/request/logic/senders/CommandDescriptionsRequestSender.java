package request.logic.senders;

import command.logic.CommandDescription;
import command.logic.CommandDescriptionHolder;
import core.providers.SingleElementProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requests.CommandDescriptionsRequest;
import response.logic.ApplicationResponseProvider;
import responses.BaseResponse;
import responses.CommandDescriptionsResponse;
import server.logic.ServerConnectionHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CommandDescriptionsRequestSender implements ApplicationResponseProvider<BaseResponse> {

    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6");

    private final RequestSender requestSender;

    {
        requestSender = new RequestSender();
    }

    private SingleElementProvider<ArrayList<CommandDescription>>[] providers;

    @SafeVarargs
    public final void sendRequestForGetCommands(SingleElementProvider<ArrayList<CommandDescription>>... providers) {
        this.providers = providers;
        CommandDescriptionsRequest request = new CommandDescriptionsRequest();
        try {
            requestSender.sendRequest(request, ServerConnectionHandler.getCurrentConnection(), this);
        } catch (IOException e) {
            logger.error("Something went wrong during I/O operations.", e);
        }
    }

    @Override
    public void acceptResponse(BaseResponse response) {
        if (response.getClass().equals(CommandDescriptionsResponse.class)) {
            ArrayList<CommandDescription> result;
            CommandDescriptionsResponse acceptedResponse = (CommandDescriptionsResponse) response;
            result = acceptedResponse.getCommands();

            CommandDescriptionHolder.initialize(result);

            ArrayList<CommandDescription> finalResult = result;
            Arrays.stream(providers).forEach(x -> x.acceptElement(finalResult));
            requestSender.removeListener(this);
        }
    }
    @Override
    public void acceptException(Exception e) {
        Arrays.stream(providers).forEach(x -> x.acceptException(e));
    }
}
