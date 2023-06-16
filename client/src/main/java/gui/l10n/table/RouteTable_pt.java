package gui.l10n.table;

import java.util.ListResourceBundle;

public class RouteTable_pt extends ListResourceBundle {

    private final Object[][] contents = {
            {
                    "c_id", "id"
            },
            {
                    "c_name", "nome"
            },
            {
                    "c_coordinates.x", "coordenadas.x"
            },
            {
                    "c_coordinates.y", "coordenadas.y"
            },
            {
                    "c_creation_date", "data_criacao"
            },
            {
                    "c_from.x", "de.x"
            },
            {
                    "c_from.y", "de.y"
            },
            {
                    "c_from.z", "de.z"
            },
            {
                    "c_from.name", "de.nome"
            },
            {
                    "c_to.x", "para.x"
            },
            {
                    "c_to.y", "para.y"
            },
            {
                    "c_to.z", "para.z"
            },
            {
                    "c_to.name", "para.nome"
            },
            {
                    "c_distance", "distancia"
            },
            {
                    "invalid_obj_warn", "Objeto inválido. Tente corrigir todos os campos em vermelho antes de confirmar."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
