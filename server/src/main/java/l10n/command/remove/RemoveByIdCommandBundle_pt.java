package l10n.command.remove;

import java.util.ListResourceBundle;

public class RemoveByIdCommandBundle_pt extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "Element with that id doesn't exists or you don't have access to edit this object.",
                    "O elemento com esse ID não existe ou você não tem permissão para editar este objeto."
            },
            {
                    "Executed", "Executado"
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
