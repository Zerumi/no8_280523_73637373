package command.manager.commands.intrface;

/**
 * Provides Argument Consumer
 *
 * @param <T> Argument param
 * @author zerumi
 * @since 2.1
 */
public interface ArgumentConsumer<T> {
    void setObj(T obj);
}
