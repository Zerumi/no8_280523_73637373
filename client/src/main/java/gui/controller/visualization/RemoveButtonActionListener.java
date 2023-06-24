package gui.controller.visualization;

import model.Route;
import model.RouteFields;
import request.RemoveFromCollectionRequest;
import request.logic.sender.SuppressResponseRequestSender;
import server.logic.ServerConnectionHandler;
import util.RouteFieldSetters;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RemoveButtonActionListener implements ActionListener {
    private final ArrayList<JTextField> editFields;

    public RemoveButtonActionListener(ArrayList<JTextField> fields) {
        this.editFields = fields;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Route route = new Route();
        for (int i = 0; i < editFields.size(); i++) {
            editFields.get(i).setEnabled(false);
            RouteFieldSetters.setValue(route, RouteFields.byId(i), editFields.get(i).getText());
        }
        new SuppressResponseRequestSender().sendRequestAndSuppressResponse(new RemoveFromCollectionRequest(route.getId()), ServerConnectionHandler.getCurrentConnection());
    }
}
