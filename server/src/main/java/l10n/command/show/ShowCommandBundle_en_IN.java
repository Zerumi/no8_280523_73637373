package l10n.command.show;

import java.util.ListResourceBundle;

public class ShowCommandBundle_en_IN extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "You must enter a valid page number",
                    "You must enter a valid page number"
            },
            {
                    "large_collection", "The collection is too large. Sending only 50 elements. Use show [page] for variate displaying."
            },
            {
                    "There's nothing to show.",
                    "There's nothing to show."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
