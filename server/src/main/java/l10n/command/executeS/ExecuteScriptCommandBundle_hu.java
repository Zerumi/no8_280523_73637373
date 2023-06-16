package l10n.command.executeS;

import java.util.ListResourceBundle;

public class ExecuteScriptCommandBundle_hu extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "Server is alive and ready for command executing!",
                    "A szerver él és kész a parancsok végrehajtására!"
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
