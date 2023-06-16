package l10n.command.info;

import java.util.ListResourceBundle;

public class InfoCommandBundle_en_IN extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "info", """
Now you are operating with collection of type {0}, filled with elements of type {1}
Size of the collection is {2}
Init date: {3}"""
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
