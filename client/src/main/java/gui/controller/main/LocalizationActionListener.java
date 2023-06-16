package gui.controller.main;

import gui.controller.main.callback.LocalizationCallback;
import gui.controller.main.callback.RepaintCallback;
import util.LocaleHolder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.prefs.Preferences;

public class LocalizationActionListener implements ActionListener {

    private final String locale;
    private final LocalizationCallback callback;

    public LocalizationActionListener(String locale, LocalizationCallback callback) {
        this.locale = locale;
        this.callback = callback;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Preferences root = Preferences.userRoot();
        Preferences node = root.node("/com/zerumi/github/lab8");
        Locale newLocale = Locale.forLanguageTag(locale);
        node.put("default_locale", locale);
        LocaleHolder.setLocale(newLocale);
        Locale.setDefault(newLocale);
        callback.changeLocale(newLocale);
        callback.callRepaint();
    }
}
