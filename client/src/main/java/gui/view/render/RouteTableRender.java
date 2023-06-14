package gui.view.render;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class RouteTableRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
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
