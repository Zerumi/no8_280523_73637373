package l10n.command.executeS;

import java.util.ListResourceBundle;

public class ExecuteScriptCommandBundle_ru extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "Server is alive and ready for command executing!",
                    "Сервер поднят и готов исполнять команды!"
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
