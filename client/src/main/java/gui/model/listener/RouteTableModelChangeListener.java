package gui.model.listener;

import gui.model.RouteTableModel;
import response.logic.ApplicationResponseProvider;
import response.BaseResponse;
import response.CollectionUpdatedResponse;

import java.awt.*;

public class RouteTableModelChangeListener implements ApplicationResponseProvider<BaseResponse> {

    private final RouteTableModel parent;

    public RouteTableModelChangeListener(RouteTableModel model) {
        this.parent = model;
    }

    @Override
    public void acceptException(Exception e) {
        parent.acceptException(e);
    }

    @Override
    public void acceptResponse(BaseResponse response) {
        if (response instanceof CollectionUpdatedResponse) {
            EventQueue.invokeLater(() -> parent.acceptCollectionUpdate(((CollectionUpdatedResponse) response)));
        }
    }
}
