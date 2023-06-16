package gui.l10n.command;

import java.util.ListResourceBundle;

public class Commands extends ListResourceBundle {

    private final Object[][] contents = {
            {
                    "help", "Help"
            },
            {
                    "info", "Info"
            },
            {
                    "show", "Show"
            },
            {
                    "remove_by_id", "Remove by id"
            },
            {
                    "clear", "Clear"
            },
            {
                    "execute_script", "Execute script"
            },
            {
                    "exit", "Logout & exit"
            },
            {
                    "min_by_creation_date", "Min by creation date"
            },
            {
                    "count_greater_than_distance", "Count greater than distance"
            },
            {
                    "print_field_ascending_distance", "Print distance ascending"
            },
            {
                    "add", "Add"
            },
            {
                    "add_if_min", "Add if min"
            },
            {
                    "add_if_max", "Add if max"
            },
            {
                    "update", "Update"
            },
            {
                    "remove_greater", "Remove greater"
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
