package l10n.command;

import java.util.ListResourceBundle;

public class CommandResourceBundle_ru extends ListResourceBundle {

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
                    "remove_by_id", "Удалить по ID"
            },
            {
                    "clear", "Очистить"
            },
            {
                    "execute_script", "Выполнить скрипт"
            },
            {
                    "exit", "Выйти"
            },
            {
                    "min_by_creation_date", "Минимальная по дате создания"
            },
            {
                    "count_greater_than_distance", "Количество элементов, больших заданной дистанции"
            },
            {
                    "print_field_ascending_distance", "Вывести дистанции по возрастанию"
            },
            {
                    "add", "Добавить"
            },
            {
                    "add_if_min", "Добавить, если минимальное"
            },
            {
                    "add_if_max", "Добавить, если максимальное"
            },
            {
                    "update", "Обновить"
            },
            {
                    "remove_greater", "Удалить большие"
            },
            {
                    "d_help", "Показывает справку о доступных командах"
            },
            {
                    "d_info", "Показывает информацию о коллекции"
            },
            {
                    "d_show", "Показывает элементы (до 50 элементов) коллекции в формате toString()"
            },
            {
                    "d_remove_by_id", "Удаляет элемент из коллекции по ID. Примечание: вы можете удалять только элементы, принадлежащие вам"
            },
            {
                    "d_clear", "Очищает коллекцию. Примечание: вы можете удалять только элементы, принадлежащие вам"
            },
            {
                    "d_execute_script", "Считывает и выполняет скрипт из файла"
            },
            {
                    "d_exit", "Завершает сеанс и выходит из приложения"
            },
            {
                    "d_min_by_creation_date", "Возвращает элемент коллекции с минимальной датой создания"
            },
            {
                    "d_count_greater_than_distance", "Показывает количество элементов, больших заданного значения дистанции"
            },
            {
                    "d_print_field_ascending_distance", "Выводит все значения поля дистанции в порядке возрастания"
            },
            {
                    "d_add", "Добавляет новый элемент в коллекцию. Привязывает созданный элемент к пользователю, который его создал"
            },
            {
                    "d_add_if_min", "Добавляет элемент, если его значение меньше минимального значения в коллекции"
            },
            {
                    "d_add_if_max", "Добавляет элемент, если его значение больше максимального значения в коллекции"
            },
            {
                    "d_update", "Обновляет элемент по его ID. Примечание: вы можете редактировать только элементы, принадлежащие вам"
            },
            {
                    "d_remove_greater", "Удаляет элементы из коллекции, большие заданного значения. Сравнение производится по дистанции. Примечание: вы можете удалять только элементы, принадлежащие вам"
            },
            {
                    "a_update", "id {элемент}"
            },
            {
                    "a_show", "[страница]"
            },
            {
                    "a_remove_greater", "{элемент}"
            },
            {
                    "a_remove_by_id", "id"
            },
            {
                    "a_execute_script", "путь_к_файлу"
            },
            {
                    "a_count_greater_than_distance", "дистанция"
            },
            {
                    "a_add_if_min", "{элемент}"
            },
            {
                    "a_add_if_max", "{элемент}"
            },
            {
                    "a_add", "{элемент}"
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
