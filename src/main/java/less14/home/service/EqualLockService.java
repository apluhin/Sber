package less14.home.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiFunction;

public class EqualLockService implements Service {

    private final Service service;
    private final Map<Object, ReentrantLock> map1 = new ConcurrentHashMap<>();


    public EqualLockService(Service service) {
        this.service = service;

    }

    @Override
    public void run(Object o) {
        map1.compute(o, getLock());
        try {
            service.run(o);
        } finally {
            map1.get(o).unlock();
            map1.compute(o, remove());
        }

    }

    private BiFunction<Object, ReentrantLock, ReentrantLock> getLock() {
        BiFunction<Object, ReentrantLock, ReentrantLock> function = new BiFunction<Object, ReentrantLock, ReentrantLock>() {
            @Override
            public ReentrantLock apply(Object o, ReentrantLock reentrantLock) {
                ReentrantLock lock = reentrantLock;
                if (lock == null) lock = new ReentrantLock();
                lock.lock();
                return lock;
            }
        };
        return function;
    }

    private BiFunction<Object, ReentrantLock, ReentrantLock> remove() {
        BiFunction<Object, ReentrantLock, ReentrantLock> function = new BiFunction<Object, ReentrantLock, ReentrantLock>() {
            @Override
            public ReentrantLock apply(Object o, ReentrantLock reentrantLock) {
                if (!(reentrantLock.isLocked() || reentrantLock.hasQueuedThreads())) {
                    return null;
                } else {
                    return reentrantLock;
                }

            }
        };
        return function;
    }



}








