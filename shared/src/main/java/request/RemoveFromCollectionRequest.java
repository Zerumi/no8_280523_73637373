package request;

import request.annotation.Authorize;

@Authorize
public class RemoveFromCollectionRequest extends BaseRequest {
    private final long elementID;

    public RemoveFromCollectionRequest(long elementID) {
        this.elementID = elementID;
    }

    public long getElementID() {
        return elementID;
    }
}
