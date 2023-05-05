import authorization.authCredentials.AuthenticationData;
import clientLogic.AuthorizeManager;
import exceptions.authorizationExceptions.AuthorizeException;
import org.junit.jupiter.api.Test;
import requestLogic.CallerBack;

import java.net.InetSocketAddress;

public class LoginTest {
    @Test
    public void test() throws AuthorizeException {
        var b = AuthorizeManager.authorize(new CallerBack(new InetSocketAddress(Main.PORT).getAddress(), Main.PORT),
                new AuthenticationData("zerumi", new char[]{'q', 'w', 'e', 'r', 't', 'y'}));
        System.out.println(b);
    }
}
