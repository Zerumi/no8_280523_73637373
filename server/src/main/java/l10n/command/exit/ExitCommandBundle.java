package l10n.command.exit;

import java.util.ListResourceBundle;

public class ExitCommandBundle extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "Prepared for exit!",
                    "Prepared for exit!"
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
