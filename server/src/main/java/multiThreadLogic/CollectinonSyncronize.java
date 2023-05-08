package multiThreadLogic;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CollectinonSyncronize {
    private static final CollectinonSyncronize instance;

    static {
        instance = new CollectinonSyncronize();
    }

    private final ReadWriteLock lock;

    {
        lock = new ReentrantReadWriteLock();
    }

    public static CollectinonSyncronize getInstance() {
        return instance;
    }

    public ReadWriteLock getLock() {
        return lock;
    }
}
