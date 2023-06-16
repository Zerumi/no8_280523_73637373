package gui.l10n.command;

import java.util.ListResourceBundle;

public class CommandWindow_ru extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "bExecute", "Выполнить"
            },
            {
                    "enter_args", "Введите аргументы: "
            },
            {
                    "status_code", "Код состояния: "
            },
            {
                    "response", "Ответ: "
            },
            {
                    "error", "Код состояния: Ошибка!"
            },
            {
                    "sww", "Что-то пошло не так..."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
