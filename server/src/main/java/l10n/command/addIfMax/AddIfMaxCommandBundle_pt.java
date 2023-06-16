package l10n.command.addIfMax;

import java.util.ListResourceBundle;

public class AddIfMaxCommandBundle_pt extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "Element added!", "Elemento adicionado!"
            },
            {
                    "Element not added", "Elemento não adicionado"
            },
            {
                    "Something went wrong during adding element. Ask server administrator for further information.",
                    "Ocorreu um erro ao adicionar o elemento. Consulte o administrador do servidor para obter mais informações."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
