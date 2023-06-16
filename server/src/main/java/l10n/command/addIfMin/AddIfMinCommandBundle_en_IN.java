package l10n.command.addIfMin;

import java.util.ListResourceBundle;

public class AddIfMinCommandBundle_en_IN extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "Element added!", "Element added!"
            },
            {
                    "Element not added", "Element not added"
            },
            {
                    "Something went wrong during adding element. Ask server administrator for further information.",
                    "Something went wrong during adding element. Ask server administrator for further information."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
