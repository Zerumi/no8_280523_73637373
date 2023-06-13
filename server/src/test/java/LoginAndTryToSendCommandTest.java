import authorization.credential.AuthenticationData;
import client.logic.AuthorizeManager;
import command.logic.CommandDescription;
import command.logic.reciever.caller.ExternalBaseReceiverCaller;
import exception.authorization.AuthorizeException;
import request.logic.CallerBack;
import request.logic.worker.RequestWorkerManager;
import request.logic.request.ServerRequest;
import request.CommandClientRequest;
import server.logic.datagram.DatagramServerConnectionFactory;

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
