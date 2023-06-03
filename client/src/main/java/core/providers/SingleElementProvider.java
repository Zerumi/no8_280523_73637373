package core.providers;

public interface SingleElementProvider<T> extends ExceptionProvider {
    void acceptElement(T element);
}
