package gui.l10n.auth;

import java.util.ListResourceBundle;

public class Auth_ru extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "winTitle", "Аутентификация"
            },
            {
                    "login", "Логин: "
            },
            {
                    "password", "Пароль: "
            },
            {
                    "bRegister", "Регистрация"
            },
            {
                    "bLogin", "Войти"
            },
            {
                    "error", "Ошибка! "
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
