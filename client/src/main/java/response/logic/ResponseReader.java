package response.logic;

import exception.GotAnErrorResponseException;
import exception.ProceedException;
import response.BaseResponse;
import response.ErrorResponse;
import response.IntermediateResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class ResponseReader {
    public BaseResponse readObject(InputStream in)
            throws IOException, ClassNotFoundException, GotAnErrorResponseException, ProceedException {
        ObjectInputStream ois = new ObjectInputStream(in);
        BaseResponse result = (BaseResponse) ois.readObject();
        if (result instanceof IntermediateResponse response) {
            result = new IntermediateResponseHandler(response)
                    .proceedIntermediateResponse();
        }
        if (result instanceof ErrorResponse)
            throw new GotAnErrorResponseException((ErrorResponse) result);
        return result;
    }

    public Class<? extends BaseResponse> resolveType(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(in);
        BaseResponse result = (BaseResponse) ois.readObject();
        return result.getClass();
    }
}
