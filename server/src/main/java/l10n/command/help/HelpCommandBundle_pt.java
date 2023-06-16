package l10n.command.help;

import java.util.ListResourceBundle;

public class HelpCommandBundle_pt extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "This command is not found in manager",
                    "Essa comando não foi encontrado no gerenciador"
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
