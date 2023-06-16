package gui.l10n.register;

import java.util.ListResourceBundle;

public class Register_ru extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "winTitle", "Регистрация"
            },
            {
                    "login", "Логин: "
            },
            {
                    "password", "Пароль: "
            },
            {
                    "username", "Имя пользователя: "
            },
            {
                    "bRegister", "Зарегистрироваться"
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
