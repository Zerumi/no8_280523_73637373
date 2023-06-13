package gui.view.render;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class RouteTableRender extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (table.isCellSelected(row, column))
            setForeground(Color.red);
        else if (table.isRowSelected(row))
            setForeground(Color.green);
        else if (table.isColumnSelected(column))
            setForeground(Color.blue);
        else
            setForeground(Color.black);

        c.setBackground(row % 2 == 0 ? new Color(213, 213, 213) : Color.WHITE);

        table.invalidate();
        table.repaint();
        return c;
    }
}
