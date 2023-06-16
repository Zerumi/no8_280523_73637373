package util;

import java.util.Arrays;
import java.util.Locale;

public class LocaleUtil {

    public static final Locale[] LOCALES = {Locale.forLanguageTag("en-US"), Locale.forLanguageTag("ru-RU"),
            Locale.forLanguageTag("pt-BR"), Locale.forLanguageTag("hu-HU"), Locale.forLanguageTag("en_IN")};

    public static boolean isSelect(int indexOfButton, Locale locale) {
        return Arrays.asList(LOCALES).indexOf(locale) == indexOfButton;
    }
}
