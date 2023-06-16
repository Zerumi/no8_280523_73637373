package gui.l10n.exception;

import java.util.ListResourceBundle;

public class Exceptions_ru extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "wrong_password", "Неверный логин или пароль!"
            },
            {
                    "unknown_request_class", "Неизвестный запрос. Возможно, это связано с устаревшим клиентом."
            },
            {
                    "cannot_proceed", "Не удалось выполнить запрос."
            },
            {
                    "register_error", "Произошла ошибка при регистрации"
            },
            {
                    "auth_error", "Произошла ошибка при аутентификации"
            },
            {
                    "unauthorized", "Ваша сессия истекла. Пожалуйста, выполните вход заново."
            },
            {
                    "unknown_login", "Неизвестный логин. Пожалуйста, зарегистрируйтесь"
            },
            {
                    "not_enough_passlen", "Пароль должен содержать более {0} символов."
            },
            {
                    "interrupted_by_user", "Построение объекта было прервано пользователем."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
