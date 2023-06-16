package l10n.command.info;

import java.util.ListResourceBundle;

public class InfoCommandBundle_pt extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "info",
                    """
Agora você está operando com uma coleção do tipo {0}, preenchida com elementos do tipo {1}.
O tamanho da coleção é {2}.
Data de inicialização: {3}"""
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
