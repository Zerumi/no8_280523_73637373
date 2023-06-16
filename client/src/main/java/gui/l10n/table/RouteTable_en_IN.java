package gui.l10n.table;

import java.util.ListResourceBundle;

public class RouteTable_en_IN extends ListResourceBundle {

    private final Object[][] contents = {
            {
                    "c_id", "id"
            },
            {
                    "c_name", "name"
            },
            {
                    "c_coordinates.x", "coordinates.x"
            },
            {
                    "c_coordinates.y", "coordinates.y"
            },
            {
                    "c_creation_date", "creation_date"
            },
            {
                    "c_from.x", "from.x"
            },
            {
                    "c_from.y", "from.y"
            },
            {
                    "c_from.z", "from.z"
            },
            {
                    "c_from.name", "from.name"
            },
            {
                    "c_to.x", "to.x"
            },
            {
                    "c_to.y", "to.y"
            },
            {
                    "c_to.z", "to.z"
            },
            {
                    "c_to.name", "to.name"
            },
            {
                    "c_distance", "distance"
            },
            {
                    "invalid_obj_warn", "Object is invalid. Try to fix all red fields before confirming."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
