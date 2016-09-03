package less14.home.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class EqualLockService implements Service {

    private final Service service;
    private final Map<Object, ReentrantLock> map;


    public EqualLockService(Service service) {
        this.service = service;
        map = new ConcurrentHashMap<>();
    }

    @Override
    public void run(Object o) {
        map.putIfAbsent(o, new ReentrantLock());
        map.get(o).lock();
        try {
            service.run(o);
        } finally {
            ReentrantLock lock = !map.get(o).hasQueuedThreads() ? map.remove(o) : map.get(o);
            lock.unlock();
        }


    }


}
