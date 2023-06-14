package gui.controller.filler;

import gui.controller.filler.callback.ValidationCallback;
import model.RouteFields;
import model.validator.adapter.RouteFieldValidateAdaptor;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class FillTextDocumentListener implements DocumentListener {

    private final JTextField textField;
    private final RouteFields routeField;
    private final ValidationCallback callback;

    public FillTextDocumentListener(JTextField textField, RouteFields routeField, ValidationCallback callback) {
        this.textField = textField;
        this.routeField = routeField;
        this.callback = callback;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        handleInput();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        handleInput();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        handleInput();
    }

    private void handleInput() {
        if (RouteFieldValidateAdaptor.validate(routeField, textField.getText())) {
            callback.fireCorrectInput(textField, routeField);
        } else {
            callback.fireWrongInput(textField, routeField);
        }
    }
}
