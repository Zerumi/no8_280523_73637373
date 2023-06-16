package gui.l10n.exception;

import java.util.ListResourceBundle;

public class Exceptions_hu extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "wrong_password", "Hibás bejelentkezési adatok!"
            },
            {
                    "unknown_request_class", "Ismeretlen kérés. Talán az ügyfél elavult."
            },
            {
                    "cannot_proceed", "A kérés nem hajtható végre."
            },
            {
                    "register_error", "Hiba történt a regisztráció során"
            },
            {
                    "auth_error", "Hiba történt az azonosítás során"
            },
            {
                    "unauthorized", "Lejárt a munkamenet. Kérjük, jelentkezzen be újra."
            },
            {
                    "unknown_login", "Ismeretlen bejelentkezési azonosító. Kérjük, regisztráljon"
            },
            {
                    "not_enough_passlen", "A jelszónak legalább {0} karakter hosszúnak kell lennie."
            },
            {
                    "interrupted_by_user", "A tárgy építése a felhasználó által megszakítva."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
