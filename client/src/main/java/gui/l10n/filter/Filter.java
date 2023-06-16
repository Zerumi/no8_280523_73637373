package gui.l10n.filter;

import java.util.ListResourceBundle;

public class Filter extends ListResourceBundle {

    private final Object[][] contents = {
            {
                    "label", "Enter value to filter..."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
