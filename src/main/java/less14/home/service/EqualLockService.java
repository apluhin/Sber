package less14.home.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class EqualLockService implements Service {

    private final Service service;
    private final Map<Object, ReentrantLock> map;
    private final ReentrantLock nullLock;


    public EqualLockService(Service service) {
        this.service = service;
        map = new ConcurrentHashMap<>();
        nullLock = new ReentrantLock();
    }

    @Override
    public void run(Object o) {
        map.putIfAbsent(o, new ReentrantLock());
        ReentrantLock reentrantLock = map.getOrDefault(o, nullLock);
        reentrantLock.lock();
        try {
            if (!map.containsKey(o) && reentrantLock != nullLock) map.put(o, reentrantLock);
            service.run(o);
        } finally {
            if (nullLock.isHeldByCurrentThread()) {
                nullLock.unlock();
            } else {
                ReentrantLock lock = map.get(o).hasQueuedThreads() ? map.get(o) : map.remove(o);
                lock.unlock();
            }
        }


    }

}
