package l10n.command.help;

import java.util.ListResourceBundle;

public class HelpCommandBundle_hu extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "This command is not found in manager",
                    "Ez a parancs nem található a kezelőben"
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
