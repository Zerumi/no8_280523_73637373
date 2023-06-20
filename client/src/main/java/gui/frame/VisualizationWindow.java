package gui.frame;


import gui.component.VisualisationComponent;
import gui.controller.filler.FillTextDocumentListener;
import gui.controller.filler.callback.ValidationCallback;
import gui.controller.main.callback.GetCollectionFromModelCallback;
import gui.controller.visualization.UpdateButtonActionListener;
import gui.controller.visualization.callback.PrintObjInfoCallback;
import gui.controller.visualization.callback.VisualizationDisposeCallback;
import model.Route;
import model.RouteFields;
import util.RouteFieldGetUtil;
import util.SpringUtilities;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VisualizationWindow extends JFrame implements PrintObjInfoCallback, ValidationCallback {

    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("gui.l10n.table.RouteTable");
    private final VisualisationComponent component;
    private final JPanel infoPanel;
    private final ArrayList<JTextField> fields;
    private final JLabel label = new JLabel("Choose some point to get object information...");

    public VisualizationWindow(GetCollectionFromModelCallback callback) throws IOException {
        this.component = new VisualisationComponent(callback, this);
        this.fields = new ArrayList<>();

        infoPanel = new JPanel();
        infoPanel.setLayout(new SpringLayout());

        infoPanel.add(new JLabel());
        infoPanel.add(label);

        for (RouteFields routeField : RouteFields.values()) {
            JTextField field = new JTextField();
            fields.add(field);
            field.setEnabled(false);
            field.setColumns(30);
            field.getDocument().addDocumentListener(new FillTextDocumentListener(field, routeField, this));
            JLabel label = new JLabel(resourceBundle.getString("c_" + routeField.getName()), SwingConstants.RIGHT);
            infoPanel.add(label);
            infoPanel.add(field);
        }

        JButton updButt = new JButton("Update");
        updButt.addActionListener(new UpdateButtonActionListener(fields));

        infoPanel.add(new JLabel());
        infoPanel.add(updButt);

        SpringUtilities.makeCompactGrid(infoPanel, RouteFields.values().length + 2, 2, 3, 3, 0, 0);

        this.add(infoPanel, BorderLayout.EAST); // когда-нибудь я научусь угадывать с первого раза, где east, а где west...
        this.add(component, BorderLayout.CENTER);
        this.addWindowListener(new VisualizationDisposeCallback(this));
        this.setLocationByPlatform(true);
        this.pack();
    }

    @Override
    public void deliverObj(Route route) {
        for (int i = 0; i < fields.size(); i++) {
            fields.get(i).setText(RouteFieldGetUtil.getStr(route, RouteFields.byId(i)));
            if (RouteFields.byId(i) != RouteFields.ID && RouteFields.byId(i) != RouteFields.CREATION_DATE)
                fields.get(i).setEnabled(true);
        }
    }

    @Override
    public void fireWrongInput(JTextField fieldForText, RouteFields field) {
        fieldForText.setForeground(Color.RED);
    }

    @Override
    public void fireCorrectInput(JTextField fieldForText, RouteFields field) {
        fieldForText.setForeground(Color.GREEN);
    }
}
