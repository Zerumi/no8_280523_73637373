package core.provider;

public interface SingleElementProvider<T> extends ExceptionProvider {
    void acceptElement(T element);
}
