package l10n.command.countGTD;

import java.util.ListResourceBundle;

public class CountGreaterThanDistanceCommandBundle extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "Total count: ", "Total count: "
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
