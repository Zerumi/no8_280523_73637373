package gui.l10n.exception;

import java.util.ListResourceBundle;

public class Exceptions extends ListResourceBundle {
    private final Object[][] contents = {
            {
                    "wrong_password", "Wrong login or password!"
            },
            {
                    "unknown_request_class", "Unknown request. Maybe it's because your client was outdated."
            },
            {
                    "cannot_proceed", "Cannot proceed request."
            },
            {
                    "register_error", "Something went wrong during registration"
            },
            {
                    "auth_error", "Something went wrong during authentication"
            },
            {
                    "unauthorized", "Your session has expired. Please, login again."
            },
            {
                    "unknown_login", "Unknown login. Please, register"
            },
            {
                    "not_enough_passlen", "Password should be more than {0} symbols."
            },
            {
                    "interrupted_by_user", "Object building was interrupted by user."
            },
            {
                    "inaccessible", "You have no access to element."
            }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
