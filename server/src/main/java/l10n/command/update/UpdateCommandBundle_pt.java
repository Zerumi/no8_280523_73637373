package l10n.command.update;

import java.util.ListResourceBundle;

public class UpdateCommandBundle_pt extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "You must enter a valid ID",
                    "Você deve inserir um ID válido"
            },
            {
                    "User has no access to the element (or this element doesn't exists)",
                    "O usuário não tem acesso ao elemento (ou este elemento não existe)"
            },
            {
                    "Element updated!",
                    "Elemento atualizado!"
            },
            {
                    "Element with that id doesn't exists.",
                    "Elemento com esse ID não existe."
            },
            {
                    "Something went wrong during updating element. Ask server administrator for further information.",
                    "Ocorreu um erro ao atualizar o elemento. Solicite informações adicionais ao administrador do servidor."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
