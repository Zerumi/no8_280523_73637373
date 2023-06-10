import authorization.authCredentials.AuthenticationData;
import clientLogic.AuthorizeManager;
import commandLogic.CommandDescription;
import commandLogic.commandReceiverLogic.callers.ExternalBaseReceiverCaller;
import exceptions.authorizationExceptions.AuthorizeException;
import request.logic.CallerBack;
import request.logic.workers.RequestWorkerManager;
import request.logic.requests.ServerRequest;
import requests.CommandClientRequest;
import serverLogic.DatagramServerConnectionFactory;

import java.net.InetSocketAddress;

public class LoginAndTryToSendCommandTest {
    public void test() throws AuthorizeException {
        var b = AuthorizeManager.authorize(new CallerBack(new InetSocketAddress(Main.PORT).getAddress(), Main.PORT),
                new AuthenticationData("zerumi", new char[]{'q', 'w', 'e', 'r', 't', 'y'}));
        System.out.println(b);

        new RequestWorkerManager().workWithRequest(new ServerRequest(new CommandClientRequest(
                new CommandDescription("info", new ExternalBaseReceiverCaller()), new String[]{"info"}),
                new CallerBack(new InetSocketAddress(Main.PORT).getAddress(), Main.PORT),
                new DatagramServerConnectionFactory().initializeServer(Main.PORT)));
    }
}
