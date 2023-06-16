package gui.l10n.auth;

import java.util.ListResourceBundle;

public class Auth extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "winTitle", "Authentication"
            },
            {
                    "login", "Login: "
            },
            {
                    "password", "Password: "
            },
            {
                    "bRegister", "Register"
            },
            {
                    "bLogin", "Login"
            },
            {
                    "error", "Error! "
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
