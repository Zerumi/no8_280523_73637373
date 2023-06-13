import authorization.credential.RegistrationData;
import client.logic.AuthorizeManager;
import exception.authorization.AuthorizeException;
import request.logic.CallerBack;

import java.net.InetSocketAddress;

public class RegisterTest {
    public void test() throws AuthorizeException {
        var a = AuthorizeManager.register(new CallerBack(new InetSocketAddress(Main.PORT).getAddress(), Main.PORT),
                new RegistrationData("Kirill", "zerumi", new char[]{'q', 'w', 'e', 'r', 't', 'y'}));
        System.out.println(a);
    }
}
