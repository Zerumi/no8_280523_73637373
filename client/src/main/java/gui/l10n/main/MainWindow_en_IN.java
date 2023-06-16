package gui.l10n.main;

import java.util.ListResourceBundle;

public class MainWindow_en_IN extends ListResourceBundle {

    private final Object[][] contents = {
            {
                    "user_show", "Authorized as {0}. Welcome back, {1}!"
            },
            {
                    "available_commands", "Available commands:"
            },
            {
                    "visualisation", "Visualization"
            },
            {
                    "ct_visualisation", "Show visualisation"
            },
            {
                    "manage", "Manage"
            },
            {
                    "ct_manage", "Manage application"
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
