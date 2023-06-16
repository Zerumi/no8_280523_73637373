package gui.l10n.table;

import java.util.ListResourceBundle;

public class RouteTable_ru extends ListResourceBundle {

    private final Object[][] contents = {
            {
                    "c_id", "ид"
            },
            {
                    "c_name", "название"
            },
            {
                    "c_coordinates.x", "координаты.x"
            },
            {
                    "c_coordinates.y", "координаты.y"
            },
            {
                    "c_creation_date", "дата_создания"
            },
            {
                    "c_from.x", "откуда.x"
            },
            {
                    "c_from.y", "откуда.y"
            },
            {
                    "c_from.z", "откуда.z"
            },
            {
                    "c_from.name", "откуда.название"
            },
            {
                    "c_to.x", "куда.x"
            },
            {
                    "c_to.y", "куда.y"
            },
            {
                    "c_to.z", "куда.z"
            },
            {
                    "c_to.name", "куда.название"
            },
            {
                    "c_distance", "расстояние"
            },
            {
                    "invalid_obj_warn", "Объект недопустим. Попробуйте исправить все красные поля перед подтверждением."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
