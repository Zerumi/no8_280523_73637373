package request;

import model.Route;
import request.annotation.Authorize;

@Authorize
public class UpdateElementRequest extends BaseRequest {
    private final Route newRoute;

    public UpdateElementRequest(Route newRoute) {
        this.newRoute = newRoute;
    }

    public Route getNewRoute() {
        return newRoute;
    }
}
