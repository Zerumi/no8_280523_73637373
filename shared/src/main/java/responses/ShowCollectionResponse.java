package responses;

import models.Route;

import java.util.HashSet;

public class ShowCollectionResponse extends BaseResponse {
    private final HashSet<Route> collection;

    public ShowCollectionResponse(HashSet<Route> collection) {
        this.collection = collection;
    }

    public HashSet<Route> getCollection() {
        return collection;
    }
}
