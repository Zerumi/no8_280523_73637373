package l10n.command.addIfMin;

import java.util.ListResourceBundle;

public class AddIfMinCommandBundle_hu extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "Element added!", "Elem hozzáadva!"
            },
            {
                    "Element not added", "Elem nem lett hozzáadva"
            },
            {
                    "Something went wrong during adding element. Ask server administrator for further information.",
                    "Hiba történt az elem hozzáadása közben. Kérj segítséget a szerver adminisztrátorától további információért."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
