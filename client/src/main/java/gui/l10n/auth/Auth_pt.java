package gui.l10n.auth;

import java.util.ListResourceBundle;

public class Auth_pt extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "winTitle", "Autenticação"
            },
            {
                    "login", "Login: "
            },
            {
                    "password", "Senha: "
            },
            {
                    "bRegister", "Registrar"
            },
            {
                    "bLogin", "Entrar"
            },
            {
                    "error", "Erro! "
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
