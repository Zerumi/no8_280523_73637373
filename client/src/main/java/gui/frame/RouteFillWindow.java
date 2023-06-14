package gui.frame;

import gui.controller.filler.FillTextDocumentListener;
import gui.controller.filler.callback.ValidationCallback;
import model.Coordinates;
import model.Location;
import model.Route;
import model.RouteFields;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.RouteFieldSetters;
import util.SpringUtilities;

import javax.swing.*;
import java.awt.*;

public class RouteFillWindow extends JPanel implements ValidationCallback {

    private static final Logger logger = LogManager.getLogger("com.github.zerumi.lab8");

    private final Route route;
    private final JButton confirmButton;
    private JDialog fillDialog;
    private boolean ok;

    public RouteFillWindow() {
        route = new Route();
        route.setCoordinates(new Coordinates());
        route.setFrom(new Location());
        route.setTo(new Location());
        this.setLayout(new BorderLayout());

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new SpringLayout());

        for (RouteFields routeField : RouteFields.values()) {
            JTextField field = new JTextField();
            field.setColumns(30);
            field.getDocument().addDocumentListener(new FillTextDocumentListener(field, routeField, this));
            JLabel label = new JLabel(routeField.getName(), SwingConstants.RIGHT);
            fieldPanel.add(label);
            fieldPanel.add(field);
        }

        JPanel confirmButtonPanel = new JPanel();

        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> {
            ok = true;
            fillDialog.setVisible(false);
        });
        confirmButtonPanel.add(confirmButton);

        SpringUtilities.makeCompactGrid(fieldPanel, RouteFields.values().length, 2, 3, 3, 0, 0);

        this.add(fieldPanel, BorderLayout.CENTER);
        this.add(confirmButtonPanel, BorderLayout.SOUTH);
        this.validate();
    }


    public boolean showAsDialog(String title) {
        ok = false;

        fillDialog = new JDialog((Frame) null, true);
        fillDialog.add(this);
        fillDialog.getRootPane().setDefaultButton(confirmButton);
        fillDialog.pack();

        fillDialog.setTitle(title);
        fillDialog.setVisible(true);
        return ok;
    }

    public Route getRoute() {
        logger.info(route.toString());
        return route;
    }

    @Override
    public void fireWrongInput(JTextField fieldForText, RouteFields field) {
        fieldForText.setForeground(Color.RED);
    }

    @Override
    public void fireCorrectInput(JTextField fieldForText, RouteFields field) {
        fieldForText.setForeground(Color.GREEN);
        RouteFieldSetters.setValue(route, field, fieldForText.getText());
    }
}
