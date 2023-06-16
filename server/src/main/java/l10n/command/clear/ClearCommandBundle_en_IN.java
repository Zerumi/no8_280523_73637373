package l10n.command.clear;

import java.util.ListResourceBundle;

public class ClearCommandBundle_en_IN extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "Something went wrong during removing elements. Ask server administrator for further information.",
                    "Something went wrong during removing elements. Ask server administrator for further information."
            },
            {
                    "removed elements", "Removed {0} of {1} available elements."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
