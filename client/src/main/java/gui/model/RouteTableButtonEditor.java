package gui.model;

import core.provider.ExceptionProvider;
import gui.l10n.exception.ExceptionLocalizer;
import request.logic.sender.RemoveFromCollectionRequestSender;

import javax.swing.*;
import java.awt.*;

public class RouteTableButtonEditor extends DefaultCellEditor implements ExceptionProvider {
    protected JButton button;

    private String label;

    private boolean isPushed;

    private long currentID;

    public RouteTableButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> fireEditingStopped());
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        currentID = Long.parseLong(String.valueOf(table.getValueAt(row, 0)));
        return button;
    }

    public Object getCellEditorValue() {
        if (isPushed) {
            new RemoveFromCollectionRequestSender().sendRemoveRequest(currentID, this);
        }
        isPushed = false;
        return label;
    }

    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }

    @Override
    public void acceptException(Exception e) {
        JOptionPane.showMessageDialog(button, ExceptionLocalizer.localizeException(e));
    }
}
