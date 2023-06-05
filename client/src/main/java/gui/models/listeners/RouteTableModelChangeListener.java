package gui.models.listeners;

import gui.models.RouteTableModel;
import responseLogic.ApplicationResponseProvider;
import responses.BaseResponse;
import responses.CollectionUpdatedResponse;

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
            parent.acceptCollectionUpdate(((CollectionUpdatedResponse) response));
        }
    }
}
