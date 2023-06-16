package gui.l10n.main;

import java.util.ListResourceBundle;

public class MainWindow_ru extends ListResourceBundle {

    private final Object[][] contents = {
            {
                    "user_show", "Авторизован как {0}. Добро пожаловать, {1}!"
            },
            {
                    "available_commands", "Доступные команды:"
            },
            {
                    "visualisation", "Визуализация"
            },
            {
                    "ct_visualisation", "Показать визуализацию"
            },
            {
                    "manage", "Управление"
            },
            {
                    "ct_manage", "Управление приложением"
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
