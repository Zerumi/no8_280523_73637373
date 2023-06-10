import authorization.authCredentials.AuthenticationData;
import clientLogic.AuthorizeManager;
import exceptions.authorizationExceptions.AuthorizeException;
import request.logic.CallerBack;

import java.net.InetSocketAddress;

public class LoginTest {
    public void test() throws AuthorizeException {
        var b = AuthorizeManager.authorize(new CallerBack(new InetSocketAddress(Main.PORT).getAddress(), Main.PORT),
                new AuthenticationData("zerumi", new char[]{'q', 'w', 'e', 'r', 't', 'y'}));
        System.out.println(b);
    }
}
