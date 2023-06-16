package gui.l10n.command;

import java.util.ListResourceBundle;

public class CommandWindow_hu extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "bExecute", "Végrehajtás"
            },
            {
                    "enter_args", "Adjon meg argumentumokat: "
            },
            {
                    "status_code", "Státuszkód: "
            },
            {
                    "response", "Válasz: "
            },
            {
                    "error", "Státuszkód: Hiba!"
            },
            {
                    "sww", "Valami hiba történt..."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
