package l10n.command.update;

import java.util.ListResourceBundle;

public class UpdateCommandBundle_hu extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "You must enter a valid ID",
                    "Meg kell adnia egy érvényes azonosítót"
            },
            {
                    "User has no access to the element (or this element doesn't exists)",
                    "A felhasználónak nincs hozzáférése az elemhez (vagy ez az elem nem létezik)"
            },
            {
                    "Element updated!",
                    "Elem frissítve!"
            },
            {
                    "Element with that id doesn't exists.",
                    "Az azonosítóval rendelkező elem nem létezik."
            },
            {
                    "Something went wrong during updating element. Ask server administrator for further information.",
                    "Hiba történt az elem frissítése közben. További információért kérdezze meg a szerver rendszergazdáját."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
