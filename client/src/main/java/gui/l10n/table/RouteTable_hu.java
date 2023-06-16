package gui.l10n.table;

import java.util.ListResourceBundle;

public class RouteTable_hu extends ListResourceBundle {

    private final Object[][] contents = {
            {
                    "c_id", "azonosító"
            },
            {
                    "c_name", "név"
            },
            {
                    "c_coordinates.x", "koordináták.x"
            },
            {
                    "c_coordinates.y", "koordináták.y"
            },
            {
                    "c_creation_date", "létrehozás_dátuma"
            },
            {
                    "c_from.x", "kezdőpont.x"
            },
            {
                    "c_from.y", "kezdőpont.y"
            },
            {
                    "c_from.z", "kezdőpont.z"
            },
            {
                    "c_from.name", "kezdőpont.név"
            },
            {
                    "c_to.x", "végpont.x"
            },
            {
                    "c_to.y", "végpont.y"
            },
            {
                    "c_to.z", "végpont.z"
            },
            {
                    "c_to.name", "végpont.név"
            },
            {
                    "c_distance", "távolság"
            },
            {
                    "invalid_obj_warn", "Az objektum érvénytelen. Kérjük, javítsa az összes piros mezőt, mielőtt megerősíti."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
