package gui.models.listeners;

import gui.models.RouteTableModel;
import response.logic.ApplicationResponseProvider;
import responses.ShowCollectionResponse;

import java.awt.*;

public class RouteTableModelUpdateFullCollectionListener
        implements ApplicationResponseProvider<ShowCollectionResponse> {

    private final RouteTableModel parent;

    public RouteTableModelUpdateFullCollectionListener(RouteTableModel model) {
        this.parent = model;
    }

    @Override
    public void acceptException(Exception e) {
        parent.acceptException(e);
    }

    @Override
    public void acceptResponse(ShowCollectionResponse response) {
        EventQueue.invokeLater(() -> parent.acceptFullCollectionResponse(response));
    }
}
