package gui.l10n.command;

import java.util.ListResourceBundle;

public class CommandWindow_en_IN extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "bExecute", "Execute"
            },
            {
                    "enter_args", "Enter arguments: "
            },
            {
                    "status_code", "Status code: "
            },
            {
                    "response", "Response: "
            },
            {
                    "error", "Status Code: Error!"
            },
            {
                    "sww", "Something went wrong..."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
