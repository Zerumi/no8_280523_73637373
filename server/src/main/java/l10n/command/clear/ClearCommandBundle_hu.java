package l10n.command.clear;

import java.util.ListResourceBundle;

public class ClearCommandBundle_hu extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "Something went wrong during removing elements. Ask server administrator for further information.",
                    "Hiba történt az elemek eltávolítása közben. Kérj segítséget a szerver adminisztrátorától további információért."
            },
            {
                    "removed elements", "Eltávolítva: {0} elem a rendelkezésre álló {1} elem közül."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
