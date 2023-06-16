package gui.l10n.command;

import java.util.ListResourceBundle;

public class Commands_pt extends ListResourceBundle {

    private final Object[][] contents = {
            {
                    "help", "Ajuda"
            },
            {
                    "info", "Informações"
            },
            {
                    "show", "Mostrar"
            },
            {
                    "remove_by_id", "Remover por ID"
            },
            {
                    "clear", "Limpar"
            },
            {
                    "execute_script", "Executar script"
            },
            {
                    "exit", "Sair"
            },
            {
                    "min_by_creation_date", "Mínimo por data de criação"
            },
            {
                    "count_greater_than_distance", "Contar maior que distância"
            },
            {
                    "print_field_ascending_distance", "Imprimir distância ascendente"
            },
            {
                    "add", "Adicionar"
            },
            {
                    "add_if_min", "Adicionar se mínimo"
            },
            {
                    "add_if_max", "Adicionar se máximo"
            },
            {
                    "update", "Atualizar"
            },
            {
                    "remove_greater", "Remover maiores"
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
