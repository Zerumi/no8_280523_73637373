package multiThreadLogic;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ResponseSendMTLogic {
    private static final Executor executor;

    static {
        executor = Executors.newWorkStealingPool();
    }

    public static Executor getExecutor() {
        return executor;
    }
}
