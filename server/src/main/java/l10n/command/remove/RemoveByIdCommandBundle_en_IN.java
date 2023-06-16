package l10n.command.remove;

import java.util.ListResourceBundle;

public class RemoveByIdCommandBundle_en_IN extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "Element with that id doesn't exists or you don't have access to edit this object.",
                    "Element with that id doesn't exists or you don't have access to edit this object."
            },
            {
                    "Executed", "Executed"
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
