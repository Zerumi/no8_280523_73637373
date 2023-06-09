package request.logic.sender;

import command.logic.CommandDescription;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request.CommandClientRequest;
import request.LocalizedCommandClientRequest;
import response.BaseResponse;
import response.CommandStatusResponse;
import response.logic.ApplicationResponseProvider;
import server.logic.abstrct.ServerConnection;
import util.LocaleHolder;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

public class CommandRequestSender implements ApplicationResponseProvider<BaseResponse> {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6");

    private ApplicationResponseProvider<CommandStatusResponse>[] providers;
    private final RequestSender requestSender;

    {
        requestSender = new RequestSender();
    }

    @SafeVarargs
    public final void sendCommand(CommandDescription command, String[] args, ServerConnection connection, ApplicationResponseProvider<CommandStatusResponse>... providers) {
        this.providers = providers;
        try {
            var rq = new LocalizedCommandClientRequest(new CommandClientRequest(command, args), LocaleHolder.getLocale());
            logger.info("Sending command request...");
            requestSender.sendRequest(rq, connection, this);
        } catch (IOException e) {
            logger.error("Something went wrong during I/O operations", e);
        }
    }

    @Override
    public void acceptException(Exception e) {
        Arrays.stream(providers).forEach(x -> x.acceptException(e));
    }

    @Override
    public void acceptResponse(BaseResponse response) {
        if (response instanceof CommandStatusResponse acceptedResponse) {
            Arrays.stream(providers).forEach(x -> x.acceptResponse(acceptedResponse));
            requestSender.removeListener(this);
        }
    }
}
