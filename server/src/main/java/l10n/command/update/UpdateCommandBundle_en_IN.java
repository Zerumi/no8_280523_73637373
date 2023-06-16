package l10n.command.update;

import java.util.ListResourceBundle;

public class UpdateCommandBundle_en_IN extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "You must enter a valid ID",
                    "You must enter a valid ID"
            },
            {
                    "User has no access to the element (or this element doesn't exists)",
                    "User has no access to the element (or this element doesn't exists)"
            },
            {
                    "Element updated!",
                    "Element updated!"
            },
            {
                    "Element with that id doesn't exists.",
                    "Element with that id doesn't exists."
            },
            {
                    "Something went wrong during updating element. Ask server administrator for further information.",
                    "Something went wrong during updating element. Ask server administrator for further information."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
