package gui.l10n.command;

import java.util.ListResourceBundle;

public class CommandWindow_pt extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "bExecute", "Executar"
            },
            {
                    "enter_args", "Digite os argumentos: "
            },
            {
                    "status_code", "Código de status: "
            },
            {
                    "response", "Resposta: "
            },
            {
                    "error", "Código de Status: Erro!"
            },
            {
                    "sww", "Algo deu errado..."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
