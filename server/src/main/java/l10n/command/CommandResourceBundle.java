package l10n.command;

import java.util.ListResourceBundle;

public class CommandResourceBundle extends ListResourceBundle {

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
                },
                {
                        "d_help", "Shows reference about available commands"
                },
                {
                        "d_info", "Shows information about the collection"
                },
                {
                        "d_show", "Shows elements (up to 50 elements) of the collection in toString() interpretation."
                },
                {
                        "d_remove_by_id", "Removes element from collection by id. N/B: you may only remove elements belongs to you"
                },
                {
                        "d_clear", "Clears the collection. N/B: you may only remove elements belongs to you"
                },
                {
                        "d_execute_script", "Reads and executes script from file"
                },
                {
                        "d_exit", "Invoke a logout and terminate the application"
                },
                {
                        "d_min_by_creation_date", "Returns element from collection with min creation date"
                },
                {
                        "d_count_greater_than_distance", "Shows count of the elements greater than distance value"
                },
                {
                        "d_print_field_ascending_distance", "Prints all distance fields in ascending sorting"
                },
                {
                        "d_add", "Adds new element to collection. It also attach created element with user " +
                        "who created it"
                },
                {
                        "d_add_if_min", "Adds element if it's value lower than min value. Min value takes from full collection"
                },
                {
                        "d_add_if_max", "Add element if it's value greater than max value. Max value takes from all collection"
                },
                {
                        "d_update", "Updates element by it ID. N/B: you may only edit elements belongs to you"
                },
                {
                        "d_remove_greater", "Removes elements from collection greater than given in argument. Comparing is set by distance. " +
                        "N/B: you may only remove elements belongs to you"
                },
                {
                        "a_update", "id {element}"
                },
                {
                        "a_show", "[page]"
                },
                {
                        "a_remove_greater", "{element}"
                },
                {
                        "a_remove_by_id", "id"
                },
                {
                        "a_execute_script", "file_path"
                },
                {
                        "a_count_greater_than_distance", "distance"
                },
                {
                        "a_add_if_min", "{element}"
                },
                {
                        "a_add_if_max", "{element}"
                },
                {
                        "a_add", "{element}"
                }
        };

        @Override
        protected Object[][] getContents() {
                return contents;
        }
}
