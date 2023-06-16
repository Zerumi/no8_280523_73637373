package util;

import java.util.Locale;

public class LocaleHolder {
    private static Locale localeValue;

    public static Locale getLocale() {
        return localeValue;
    }

    public static void setLocale(Locale locale) {
        localeValue = locale;
    }
}
