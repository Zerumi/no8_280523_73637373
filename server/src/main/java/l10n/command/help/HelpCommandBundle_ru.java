package l10n.command.help;

import java.util.ListResourceBundle;

public class HelpCommandBundle_ru extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "This command is not found in manager",
                    "Этой команды не найдено в менеджере команд"
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
