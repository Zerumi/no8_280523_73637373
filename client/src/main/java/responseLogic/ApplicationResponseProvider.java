package responseLogic;

import core.providers.ExceptionProvider;
import responses.BaseResponse;

public interface ApplicationResponseProvider<T extends BaseResponse> extends ExceptionProvider {
    void acceptResponse(T response);
}
