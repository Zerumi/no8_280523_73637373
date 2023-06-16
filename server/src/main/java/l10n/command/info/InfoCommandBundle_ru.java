package l10n.command.info;

import java.util.ListResourceBundle;

public class InfoCommandBundle_ru extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "info", """
Сейчас вы работаете с коллекцией типа {0}, заполненной элементами типа {1}
Размер коллекции: {2}
Дата инициализации: {3}"""
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
