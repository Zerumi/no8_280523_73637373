package l10n.command.executeS;

import java.util.ListResourceBundle;

public class ExecuteScriptCommandBundle extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "Server is alive and ready for command executing!",
                    "Server is alive and ready for command executing!"
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
