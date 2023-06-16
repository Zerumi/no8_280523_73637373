package l10n.command;

import java.util.ListResourceBundle;

public class CommandResourceBundle_pt extends ListResourceBundle {

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
                    "print_field_ascending_distance", "Imprimir campo de distância em ordem ascendente"
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
                    "remove_greater", "Remover maior"
            },
            {
                    "d_help", "Mostra informações sobre os comandos disponíveis"
            },
            {
                    "d_info", "Mostra informações sobre a coleção"
            },
            {
                    "d_show", "Mostra elementos (até 50 elementos) da coleção em interpretação toString()"
            },
            {
                    "d_remove_by_id", "Remove elemento da coleção por ID. N/B: você só pode remover elementos que pertencem a você"
            },
            {
                    "d_clear", "Limpa a coleção. N/B: você só pode remover elementos que pertencem a você"
            },
            {
                    "d_execute_script", "Lê e executa um script a partir de um arquivo"
            },
            {
                    "d_exit", "Faz logout e encerra a aplicação"
            },
            {
                    "d_min_by_creation_date", "Retorna o elemento da coleção com a menor data de criação"
            },
            {
                    "d_count_greater_than_distance", "Mostra a contagem de elementos maiores que um valor de distância"
            },
            {
                    "d_print_field_ascending_distance", "Imprime todos os campos de distância em ordem ascendente"
            },
            {
                    "d_add", "Adiciona um novo elemento à coleção. Também associa o elemento criado ao usuário que o criou"
            },
            {
                    "d_add_if_min", "Adiciona um elemento se o seu valor for menor que o valor mínimo. O valor mínimo é obtido de toda a coleção"
            },
            {
                    "d_add_if_max", "Adiciona um elemento se o seu valor for maior que o valor máximo. O valor máximo é obtido de toda a coleção"
            },
            {
                    "d_update", "Atualiza um elemento pelo seu ID. N/B: você só pode editar elementos que pertencem a você"
            },
            {
                    "d_remove_greater", "Remove elementos da coleção maiores que o valor especificado. A comparação é feita pela distância. " +
                    "N/B: você só pode remover elementos que pertencem a você"
            },
            {
                    "a_update", "id {elemento}"
            },
            {
                    "a_show", "[página]"
            },
            {
                    "a_remove_greater", "{elemento}"
            },
            {
                    "a_remove_by_id", "id"
            },
            {
                    "a_execute_script", "caminho_do_arquivo"
            },
            {
                    "a_count_greater_than_distance", "distância"
            },
            {
                    "a_add_if_min", "{elemento}"
            },
            {
                    "a_add_if_max", "{elemento}"
            },
            {
                    "a_add", "{elemento}"
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
