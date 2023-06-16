package l10n.command.removeG;

import java.util.ListResourceBundle;

public class RemoveGreaterCommandBundle extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "executed", "Removed {0} elements"
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
