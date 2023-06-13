package request;

import model.RouteFields;
import request.annotation.Authorize;

@Authorize
public class UpdateSingleFieldRequest extends BaseRequest {
    private final Long objId;
    private final RouteFields field;
    private final Object valueToSet;

    public UpdateSingleFieldRequest(Long objId, RouteFields field, Object valueToSet) {
        this.objId = objId;
        this.field = field;
        this.valueToSet = valueToSet;
    }

    public Long getObjId() {
        return objId;
    }

    public RouteFields getField() {
        return field;
    }

    public Object getValueToSet() {
        return valueToSet;
    }
}
