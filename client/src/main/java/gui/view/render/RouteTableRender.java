package gui.view.render;

import util.LocaleHolder;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.text.NumberFormat;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class RouteTableRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        if (value instanceof Number) {
            value = NumberFormat.getNumberInstance(LocaleHolder.getLocale()).format(value);
        }

        if (value instanceof java.sql.Date) {
            value = DateTimeFormatter
                    .ofLocalizedDate(FormatStyle.SHORT)
                    .localizedBy(LocaleHolder.getLocale())
                    .withZone(ZoneId.systemDefault())
                    .format(((java.sql.Date) value).toLocalDate().atStartOfDay(ZoneOffset.UTC).toInstant());
        }

        if (value instanceof java.util.Date) {
            value = DateTimeFormatter
                    .ofLocalizedDate(FormatStyle.SHORT)
                    .localizedBy(LocaleHolder.getLocale())
                    .withZone(ZoneId.systemDefault())
                    .format(((java.util.Date) value).toInstant());
        }

        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (table.isCellSelected(row, column))
            this.setForeground(Color.red);
        else
            this.setForeground(Color.black);

        this.setBackground(row % 2 == 0 ? new Color(213, 213, 213) : Color.WHITE);

        //table.invalidate();
        //table.repaint();
        // черт, чел, не делай так никогда больше

        return this;
    }
}
