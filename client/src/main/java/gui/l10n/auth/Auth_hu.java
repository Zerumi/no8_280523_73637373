package gui.l10n.auth;

import java.util.ListResourceBundle;

public class Auth_hu extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "winTitle", "Hitelesítés"
            },
            {
                    "login", "Bejelentkezés: "
            },
            {
                    "password", "Jelszó: "
            },
            {
                    "bRegister", "Regisztráció"
            },
            {
                    "bLogin", "Bejelentkezés"
            },
            {
                    "error", "Hiba! "
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
