package l10n.command.exit;

import java.util.ListResourceBundle;

public class ExitCommandBundle_ru extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "Prepared for exit!",
                    "Готово для выхода!"
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
