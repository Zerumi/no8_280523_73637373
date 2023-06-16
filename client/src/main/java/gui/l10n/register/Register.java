package gui.l10n.register;

import java.util.ListResourceBundle;

public class Register extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "winTitle", "Registration"
            },
            {
                    "login", "Login: "
            },
            {
                    "password", "Password: "
            },
            {
                    "username", "Username: "
            },
            {
                    "bRegister", "Register"
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
