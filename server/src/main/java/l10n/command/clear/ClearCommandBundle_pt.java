package l10n.command.clear;

import java.util.ListResourceBundle;

public class ClearCommandBundle_pt extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "Something went wrong during removing elements. Ask server administrator for further information.",
                    "Ocorreu um erro ao remover os elementos. Consulte o administrador do servidor para obter mais informações."
            },
            {
                    "removed elements", "Removidos {0} de {1} elementos disponíveis."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
