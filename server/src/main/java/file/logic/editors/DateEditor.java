package file.logic.editors;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditor;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * Example of editor, that uses in fileLogic to convert String into object.
 * You should register your editor class in Loader by using their static method.
 *
 * @author zerumi
 * @see java.beans.PropertyEditorManager
 * @since 1.0
 * @deprecated out of support since 3.0 / now we're maintaining database logic
 */
@Deprecated
public class DateEditor implements PropertyEditor {

    private Date result;

    {
        result = new Date();
    }
    @Override
    public void setValue(Object value) {
        result = (Date)value;
    }

    @Override
    public Object getValue() {
        return result;
    }

    @Override
    public boolean isPaintable() {
        return false;
    }

    @Override
    public void paintValue(Graphics gfx, Rectangle box) {

    }

    @Override
    public String getJavaInitializationString() {
        return null;
    }

    @Override
    public String getAsText() {
        return result.toInstant().toString();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            result = Date.from(Instant.parse(text));
        }
        catch (DateTimeParseException e)
        {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String[] getTags() {
        return new String[0];
    }

    @Override
    public Component getCustomEditor() {
        return null;
    }

    @Override
    public boolean supportsCustomEditor() {
        return false;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {}

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {

    }
}