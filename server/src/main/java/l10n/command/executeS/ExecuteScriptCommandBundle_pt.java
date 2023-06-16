package l10n.command.executeS;

import java.util.ListResourceBundle;

public class ExecuteScriptCommandBundle_pt extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "Server is alive and ready for command executing!",
                    "O servidor está ativo e pronto para a execução de comandos!"
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
