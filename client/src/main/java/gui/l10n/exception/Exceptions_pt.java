package gui.l10n.exception;

import java.util.ListResourceBundle;

public class Exceptions_pt extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "wrong_password", "Login ou senha incorretos!"
            },
            {
                    "unknown_request_class", "Requisição desconhecida. Talvez seja porque seu cliente está desatualizado."
            },
            {
                    "cannot_proceed", "Não é possível prosseguir com a solicitação."
            },
            {
                    "register_error", "Ocorreu um erro durante o registro."
            },
            {
                    "auth_error", "Ocorreu um erro durante a autenticação."
            },
            {
                    "unauthorized", "Sua sessão expirou. Por favor, faça login novamente."
            },
            {
                    "unknown_login", "Login desconhecido. Por favor, faça o registro."
            },
            {
                    "not_enough_passlen", "A senha deve ter mais de {0} caracteres."
            },
            {
                    "interrupted_by_user", "A construção do objeto foi interrompida pelo usuário."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
