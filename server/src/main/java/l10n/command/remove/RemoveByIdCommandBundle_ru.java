package l10n.command.remove;

import java.util.ListResourceBundle;

public class RemoveByIdCommandBundle_ru extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "Element with that id doesn't exists or you don't have access to edit this object.",
                    "Элемент с таким идентификатором не существует или у вас нет доступа для редактирования этого объекта."
            },
            {
                    "Executed", "Выполнено"
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
