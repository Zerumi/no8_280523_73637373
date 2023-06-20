package gui.component.util;

import java.awt.*;

public class ColorGenerator {
    public static Color getColor(long number) {
        int red = (int) ((number * 17) % 256);
        int green = (int) ((number * 23) % 256);
        int blue = (int) ((number * 29) % 256);
        return new Color(red, green, blue);
    }
}
