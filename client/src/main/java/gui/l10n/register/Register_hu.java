package gui.l10n.register;

import java.util.ListResourceBundle;

public class Register_hu extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "winTitle", "Regisztráció"
            },
            {
                    "login", "Bejelentkezés: "
            },
            {
                    "password", "Jelszó: "
            },
            {
                    "username", "Felhasználónév: "
            },
            {
                    "bRegister", "Regisztráció"
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
