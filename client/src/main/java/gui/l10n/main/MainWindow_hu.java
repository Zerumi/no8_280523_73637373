package gui.l10n.main;

import java.util.ListResourceBundle;

public class MainWindow_hu extends ListResourceBundle {

    private final Object[][] contents = {
            {
                    "user_show", "Engedélyezve: {0}. Üdvözöljük újra, {1}!"
            },
            {
                    "available_commands", "Elérhető parancsok:"
            },
            {
                    "visualisation", "Vizualizáció"
            },
            {
                    "ct_visualisation", "Vizualizáció megjelenítése"
            },
            {
                    "manage", "Kezelés"
            },
            {
                    "ct_manage", "Alkalmazás kezelése"
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
