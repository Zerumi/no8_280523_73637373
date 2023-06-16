package gui.l10n.command;

import java.util.ListResourceBundle;

public class Commands_ru extends ListResourceBundle {

    private final Object[][] contents = {
            {
                    "help", "Помощь"
            },
            {
                    "info", "Информация"
            },
            {
                    "show", "Показать"
            },
            {
                    "remove_by_id", "Удалить по идентификатору"
            },
            {
                    "clear", "Очистить"
            },
            {
                    "execute_script", "Выполнить скрипт"
            },
            {
                    "exit", "Выход"
            },
            {
                    "min_by_creation_date", "Минимальный по дате создания"
            },
            {
                    "count_greater_than_distance", "Количество больше заданного расстояния"
            },
            {
                    "print_field_ascending_distance", "Печать по возрастанию расстояния"
            },
            {
                    "add", "Добавить"
            },
            {
                    "add_if_min", "Добавить, если минимальный"
            },
            {
                    "add_if_max", "Добавить, если максимальный"
            },
            {
                    "update", "Обновить"
            },
            {
                    "remove_greater", "Удалить большие"
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
