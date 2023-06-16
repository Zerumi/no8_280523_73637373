package l10n.command.clear;

import java.util.ListResourceBundle;

public class ClearCommandBundle_ru extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "Something went wrong during removing elements. Ask server administrator for further information.",
                    "Что-то пошло не так при удалении элементов. Обратитесь к администратору сервера для получения дополнительной информации."
            },
            {
                    "removed elements", "Удалено {0} из {1} доступных элементов."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
