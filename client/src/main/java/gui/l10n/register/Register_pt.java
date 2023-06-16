package gui.l10n.register;

import java.util.ListResourceBundle;

public class Register_pt extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "winTitle", "Registro"
            },
            {
                    "login", "Login: "
            },
            {
                    "password", "Senha: "
            },
            {
                    "username", "Nome de usuário: "
            },
            {
                    "bRegister", "Registrar"
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
