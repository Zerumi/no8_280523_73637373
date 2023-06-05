package serverLogic.abstractLogic;

import core.providers.ExceptionProvider;

/**
 * Base observer interface for accepting responses
 *
 * @author zerumi
 * @since 4.0
 */
public interface ServerResponseProvider extends ExceptionProvider {
    void acceptResponse(byte[] response);
}
