package gui.controller.filler.callback;

import model.RouteFields;

import javax.swing.*;

public interface ValidationCallback {
    void fireWrongInput(JTextField fieldForText, RouteFields field);

    void fireCorrectInput(JTextField fieldForText, RouteFields field);
}
