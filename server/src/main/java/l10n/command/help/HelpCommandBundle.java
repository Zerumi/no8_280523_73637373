package l10n.command.help;

import java.util.ListResourceBundle;

public class HelpCommandBundle extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "This command is not found in manager",
                    "This command is not found in manager"
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
