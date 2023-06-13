package core.provider;

/**
 * Base exception provider for call-back tasks
 *
 * @author zerumi
 * @since 4.0
 */
public interface ExceptionProvider {
    void acceptException(Exception e);
}
