package response;

import model.Route;

import java.util.HashMap;
import java.util.HashSet;

public class ShowCollectionResponse extends BaseResponse {
    private final HashSet<Route> collection;

    private final HashMap<Long, Long> ownership;

    public ShowCollectionResponse(HashSet<Route> collection, HashMap<Long, Long> ownership) {
        this.collection = collection;
        this.ownership = ownership;
    }

    public HashMap<Long, Long> getOwnership() {
        return ownership;
    }

    public HashSet<Route> getCollection() {
        return collection;
    }
}
