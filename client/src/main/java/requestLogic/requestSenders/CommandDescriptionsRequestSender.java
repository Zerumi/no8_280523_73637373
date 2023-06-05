package requestLogic.requestSenders;

import commandLogic.CommandDescription;
import core.providers.SingleElementProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requests.CommandDescriptionsRequest;
import responseLogic.ApplicationResponseProvider;
import responses.BaseResponse;
import responses.CommandDescriptionsResponse;
import serverLogic.ServerConnectionHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CommandDescriptionsRequestSender implements ApplicationResponseProvider<BaseResponse> {

    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6");

    private SingleElementProvider<ArrayList<CommandDescription>>[] providers;

    @SafeVarargs
    public final void sendRequestForGetCommands(SingleElementProvider<ArrayList<CommandDescription>>... providers) {
        this.providers = providers;
        CommandDescriptionsRequest request = new CommandDescriptionsRequest();
        try {
            new RequestSender().sendRequest(request, ServerConnectionHandler.getCurrentConnection(), this);
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

            ArrayList<CommandDescription> finalResult = result;
            Arrays.stream(providers).forEach(x -> x.acceptElement(finalResult));
        }
    }
    @Override
    public void acceptException(Exception e) {
        Arrays.stream(providers).forEach(x -> x.acceptException(e));
    }
}
