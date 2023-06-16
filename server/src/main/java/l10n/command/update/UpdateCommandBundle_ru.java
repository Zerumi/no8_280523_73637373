package l10n.command.update;

import java.util.ListResourceBundle;

public class UpdateCommandBundle_ru extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "You must enter a valid ID",
                    "Необходимо ввести корректный ID"
            },
            {
                    "User has no access to the element (or this element doesn't exists)",
                    "У пользователя нет доступа к элементу (или этот элемент не существует)"
            },
            {
                    "Element updated!",
                    "Элемент обновлен!"
            },
            {
                    "Element with that id doesn't exists.",
                    "Элемент с указанным ID не существует."
            },
            {
                    "Something went wrong during updating element. Ask server administrator for further information.",
                    "Произошла ошибка при обновлении элемента. Обратитесь к администратору сервера для получения дополнительной информации."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
