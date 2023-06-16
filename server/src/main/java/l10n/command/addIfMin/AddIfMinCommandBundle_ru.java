package l10n.command.addIfMin;

import java.util.ListResourceBundle;

public class AddIfMinCommandBundle_ru extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "Element added!", "Элемент добавлен!"
            },
            {
                    "Element not added", "Элемент не добавлен"
            },
            {
                    "Something went wrong during adding element. Ask server administrator for further information.",
                    "При добавлении элемента произошла ошибка. Обратитесь к администратору сервера для получения дополнительной информации."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
