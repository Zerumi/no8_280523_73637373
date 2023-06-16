package gui.l10n.command;

import java.util.ListResourceBundle;

public class Commands_hu extends ListResourceBundle {

    private final Object[][] contents = {
            {
                    "help", "Segítség"
            },
            {
                    "info", "Információ"
            },
            {
                    "show", "Megjelenítés"
            },
            {
                    "remove_by_id", "Eltávolítás azonosító alapján"
            },
            {
                    "clear", "Törlés"
            },
            {
                    "execute_script", "Parancsfájl végrehajtása"
            },
            {
                    "exit", "Kijelentkezés és kilépés"
            },
            {
                    "min_by_creation_date", "Minimális létrehozási dátum"
            },
            {
                    "count_greater_than_distance", "Távolságnál nagyobb elemek száma"
            },
            {
                    "print_field_ascending_distance", "Távolság szerint növekvő sorrendben"
            },
            {
                    "add", "Hozzáadás"
            },
            {
                    "add_if_min", "Hozzáadás, ha a legkisebb"
            },
            {
                    "add_if_max", "Hozzáadás, ha a legnagyobb"
            },
            {
                    "update", "Frissítés"
            },
            {
                    "remove_greater", "Nagyobb elemek eltávolítása"
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
