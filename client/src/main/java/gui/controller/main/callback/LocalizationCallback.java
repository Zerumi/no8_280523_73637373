package gui.controller.main.callback;

import java.awt.*;
import java.util.Locale;

public interface LocalizationCallback extends RepaintCallback {

    void changeLocale(Locale locale);
}
