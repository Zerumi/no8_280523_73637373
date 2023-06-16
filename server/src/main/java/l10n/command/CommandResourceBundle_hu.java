package l10n.command;

import java.util.ListResourceBundle;

public class CommandResourceBundle_hu extends ListResourceBundle {

    private final Object[][] contents = {
            {
                    "help", "Segítség"
            },
            {
                    "info", "Információ"
            },
            {
                    "show", "Mutat"
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
                    "count_greater_than_distance", "Nagyobb távolságnál nagyobb elemek száma"
            },
            {
                    "print_field_ascending_distance", "Távolság növekvő sorrendben történő kiírása"
            },
            {
                    "add", "Hozzáadás"
            },
            {
                    "add_if_min", "Hozzáadás, ha a minimális"
            },
            {
                    "add_if_max", "Hozzáadás, ha a maximális"
            },
            {
                    "update", "Frissítés"
            },
            {
                    "remove_greater", "Nagyobb elemek eltávolítása"
            },
            {
                    "d_help", "Információ a rendelkezésre álló parancsokról"
            },
            {
                    "d_info", "Információ a gyűjteményről"
            },
            {
                    "d_show", "Elemek megjelenítése (legfeljebb 50 elem) a gyűjtemény toString() értelmezésében."
            },
            {
                    "d_remove_by_id", "Elem eltávolítása azonosító alapján. Megjegyzés: csak a saját elemeket lehet eltávolítani"
            },
            {
                    "d_clear", "A gyűjtemény törlése. Megjegyzés: csak a saját elemeket lehet eltávolítani"
            },
            {
                    "d_execute_script", "Parancsfájl beolvasása és végrehajtása fájlból"
            },
            {
                    "d_exit", "Kijelentkezés és alkalmazás leállítása"
            },
            {
                    "d_min_by_creation_date", "Elem visszaadása a legkisebb létrehozási dátummal a gyűjteményből"
            },
            {
                    "d_count_greater_than_distance", "Elemek száma, amelyek nagyobbak a megadott távolságnál"
            },
            {
                    "d_print_field_ascending_distance", "Az összes távolság mező növekvő sorrendben történő kiírása"
            },
            {
                    "d_add", "Új elem hozzáadása a gyűjteményhez. Az elemet létrehozó felhasználóval is összekapcsolja"
            },
            {
                    "d_add_if_min", "Elem hozzáadása, ha az értéke kisebb a minimális értéknél. A minimális érték a teljes gyűjteményből kerül kiválasztásra"
            },
            {
                    "d_add_if_max", "Elem hozzáadása, ha az értéke nagyobb a maximális értéknél. A maximális érték a teljes gyűjteményből kerül kiválasztásra"
            },
            {
                    "d_update", "Elem frissítése azonosító alapján. Megjegyzés: csak a saját elemeket lehet szerkeszteni"
            },
            {
                    "d_remove_greater", "Elemek eltávolítása a gyűjteményből, amelyek nagyobbak a megadottnál. Az összehasonlítás a távolsággal történik. Megjegyzés: csak a saját elemeket lehet eltávolítani"
            },
            {
                    "a_update", "azonosító {elem}"
            },
            {
                    "a_show", "[oldal]"
            },
            {
                    "a_remove_greater", "{elem}"
            },
            {
                    "a_remove_by_id", "azonosító"
            },
            {
                    "a_execute_script", "fájl_elérési_út"
            },
            {
                    "a_count_greater_than_distance", "távolság"
            },
            {
                    "a_add_if_min", "{elem}"
            },
            {
                    "a_add_if_max", "{elem}"
            },
            {
                    "a_add", "{elem}"
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
