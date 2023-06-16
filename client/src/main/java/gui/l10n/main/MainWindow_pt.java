package gui.l10n.main;

import java.util.ListResourceBundle;

public class MainWindow_pt extends ListResourceBundle {

    private final Object[][] contents = {
            {
                    "user_show", "Autenticado como {0}. Bem-vindo de volta, {1}!"
            },
            {
                    "available_commands", "Comandos disponíveis:"
            },
            {
                    "visualisation", "Visualização"
            },
            {
                    "ct_visualisation", "Mostrar visualização"
            },
            {
                    "manage", "Gerenciar"
            },
            {
                    "ct_manage", "Gerenciar aplicativo"
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
