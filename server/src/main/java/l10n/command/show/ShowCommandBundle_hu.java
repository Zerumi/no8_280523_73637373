package l10n.command.show;

import java.util.ListResourceBundle;

public class ShowCommandBundle_hu extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "You must enter a valid page number",
                    "Meg kell adnia egy érvényes oldalszámot"
            },
            {
                    "large_collection", "A gyűjtemény túl nagy. Csak 50 elemet küldök. Használja a show [oldalszám] parancsot a változó megjelenítéséhez."
            },
            {
                    "There's nothing to show.",
                    "Nincs megjelenítendő elem."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
