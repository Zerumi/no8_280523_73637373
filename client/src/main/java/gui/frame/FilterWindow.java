package gui.frame;

import gui.controller.filter.ModelConnector;
import model.RouteFields;
import util.SpringUtilities;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

public class FilterWindow extends JFrame {

    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("gui.l10n.filter.Filter");

    private final JTextField filterText = new JTextField();

    private final JComboBox<RouteFields> comboBox;

    public FilterWindow(ModelConnector connector) {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                filterText.setText(null);
                super.windowClosing(e);
            }
        });

        JLabel label = new JLabel(resourceBundle.getString("label"), SwingConstants.RIGHT);
        JPanel panel = new JPanel();
        panel.setLayout(new SpringLayout());

        JLabel chooseInvite = new JLabel("Choose field to filter:", SwingConstants.RIGHT);
        panel.add(chooseInvite);

        comboBox = new JComboBox<>();
        for (RouteFields field : RouteFields.values()) {
            comboBox.addItem(field);
        }
        comboBox.addActionListener((e) -> filterText.setText(null));
        panel.add(comboBox);

        filterText.setColumns(24);
        filterText.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        newFilter(connector);
                    }

                    public void insertUpdate(DocumentEvent e) {
                        newFilter(connector);
                    }

                    public void removeUpdate(DocumentEvent e) {
                        newFilter(connector);
                    }
                });

        panel.add(label);
        panel.add(filterText);
        SpringUtilities.makeCompactGrid(panel, 2, 2, 5, 5, 0, 0);
        this.add(panel);
        this.pack();
    }

    private void newFilter(ModelConnector connector) {
        RowFilter<TableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(filterText.getText(), comboBox.getSelectedIndex());
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        connector.getModelSorter().setRowFilter(rf);
    }
}
