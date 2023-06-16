package l10n.command.remove;

import java.util.ListResourceBundle;

public class RemoveByIdCommandBundle_hu extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "Element with that id doesn't exists or you don't have access to edit this object.",
                    "Az adott azonosítójú elem nem létezik, vagy nincs jogosultsága az objektum szerkesztéséhez."
            },
            {
                    "Executed", "Végrehajtva"
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
