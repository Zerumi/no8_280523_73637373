package gui.controller.visualization;

import model.Route;
import model.RouteFields;
import model.validator.RouteValidator;
import util.RouteFieldSetters;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UpdateButtonActionListener implements ActionListener {

    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("gui.l10n.table.RouteTable");
    private final ArrayList<JTextField> editFields;

    public UpdateButtonActionListener(ArrayList<JTextField> editFields) {
        this.editFields = editFields;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Route route = new Route();
        for (int i = 0; i < editFields.size(); i++) {
            editFields.get(i).setEnabled(false);
            RouteFieldSetters.setValue(route, RouteFields.byId(i), editFields.get(i).getText());
        }
        if (!new RouteValidator().validate(route)) {
            JOptionPane.showMessageDialog(null, resourceBundle.getString("invalid_obj_warn"), "Warning", JOptionPane.WARNING_MESSAGE);
        }

    }
}
