package serverLogic;

import core.providers.ExceptionProvider;

import java.io.InputStream;

/**
 * Base observer interface for accepting responses
 *
 * @author zerumi
 * @since 4.0
 */
public interface ServerResponseProvider extends ExceptionProvider {
    void acceptResponse(InputStream response);
}
