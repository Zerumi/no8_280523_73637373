package gui.l10n.exception;

import exception.BuildObjectException;
import exception.GotAnErrorResponseException;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class ExceptionLocalizer {
    public static String localizeException(Exception ex) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("gui.l10n.exception.Exceptions");

        String result;
        try {
            throw ex;
        } catch (GotAnErrorResponseException e) {
            String shortMsg = e.getErrorResponse().getShortMsg();
            result = resourceBundle.getString(shortMsg);
            if (shortMsg.equals("not_enough_passlen")) {
                MessageFormat mf = new MessageFormat(result);
                result = mf.format(new Object[]{e.getErrorResponse().getMsg()});
            }
        } catch (BuildObjectException e) {
            result = resourceBundle.getString(e.getMessage());
        } catch (Exception e) {
            result = e.toString();
        }
        return result;
    }
}
