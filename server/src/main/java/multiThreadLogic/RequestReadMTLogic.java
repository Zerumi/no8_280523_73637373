package multiThreadLogic;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RequestReadMTLogic {
    private static final Executor executor;

    static {
        executor = Executors.newCachedThreadPool();
    }

    public static Executor getExecutor() {
        return executor;
    }
}
