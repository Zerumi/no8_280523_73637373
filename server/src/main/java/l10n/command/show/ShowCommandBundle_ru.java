package l10n.command.show;

import java.util.ListResourceBundle;

public class ShowCommandBundle_ru extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "You must enter a valid page number",
                    "Необходимо ввести корректный номер страницы"
            },
            {
                    "large_collection",
                    "Коллекция слишком большая. Отображаются только 50 элементов. Используйте команду show [page] для разнообразного отображения."
            },
            {
                    "There's nothing to show.",
                    "Нет элементов для отображения."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
