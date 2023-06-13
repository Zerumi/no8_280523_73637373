package response.logic;

import core.provider.ExceptionProvider;
import response.BaseResponse;

public interface ApplicationResponseProvider<T extends BaseResponse> extends ExceptionProvider {
    void acceptResponse(T response);
}
