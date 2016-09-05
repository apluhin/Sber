package less14.home.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class EqualLockService implements Service {

    private final Service service;
    private final Map<Object, AtomicIntegerArray> map = new ConcurrentHashMap<>();


    public EqualLockService(Service service) {
        this.service = service;

    }

    @Override
    public void run(Object o) {

        //Array Atomic for ordering in synchronization
        map.putIfAbsent(o, new AtomicIntegerArray(new int[]{0, 0}));
        Integer current;
        current = getInteger(o);
        service.run(o);
        end(o, current);


    }

    private void end(Object o, Integer current) {
        synchronized (map.get(o)) {
            if (map.get(o).get(0) == current) {
                System.out.println(o + " " + map.remove(o) + " remove");
                return;
            }
            if (map.get(o).get(1) == 0) {
                map.get(o).set(1, 2);
            } else {
                map.get(o).incrementAndGet(1);
            }
            map.get(o).notifyAll();
        }
    }

    private Integer getInteger(Object o) {
        Integer current;
        synchronized (map.putIfAbsent(o, new AtomicIntegerArray(new int[]{0, 0}))) {
            current = map.get(o).incrementAndGet(0);
            if (map.get(o).get(0) == 1) {
                return 1;
            }
            while (map.get(o).get(1) != current) {
                try {
                    map.get(o).wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        return current;
    }


}


