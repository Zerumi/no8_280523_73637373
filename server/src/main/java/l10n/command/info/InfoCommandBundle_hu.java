package l10n.command.info;

import java.util.ListResourceBundle;

public class InfoCommandBundle_hu extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "info", """
Most egy {0} típusú gyűjteményen dolgozol, amely {1} típusú elemekkel van feltöltve
A gyűjtemény mérete {2}
Kezdeti dátum: {3}"""
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
