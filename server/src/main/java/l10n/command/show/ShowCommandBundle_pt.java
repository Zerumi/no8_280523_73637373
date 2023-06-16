package l10n.command.show;

import java.util.ListResourceBundle;

public class ShowCommandBundle_pt extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "You must enter a valid page number",
                    "Você deve inserir um número de página válido"
            },
            {
                    "large_collection",
                    "A coleção é muito grande. Enviando apenas 50 elementos. Use show [página] para exibição variada."
            },
            {
                    "There's nothing to show.",
                    "Não há nada para mostrar."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
