package less4.home.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by aleksejpluhin on 30.07.16.
 */
public class CountMapImpl<E> implements CountMap<E> {

    private final HashMap<E, Integer> map;


    public CountMapImpl() {
        this.map = new HashMap<>();

    }

    @Override
    public void add(E o) {
        if (map.get(o) != null) {
            map.put(o, map.get(o) + 1);
        } else {
            map.put(o, 1);
        }

    }

    @Override
    public int getCount(E o) {
        if (map.get(o) != null) {
            return map.get(o);
        } else {
            return 0;
        }

    }

    @Override
    public int remove(E o) {
        if (map.get(o) != null) {
            return map.remove(o);
        } else {
            return 0;
        }
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public <T extends E> void addAll(CountMap<T> source) {

        Iterator<? extends E> iterator = source.iterator();

        while (iterator.hasNext()) {
            T key = (T) iterator.next();

            if (this.map.containsKey(key)) {
                this.map.put(key, this.map.get(key) + source.getCount(key));
            } else {
                this.map.put(key, source.getCount(key));
            }

        }

    }

    @Override
    public void toMap(Map<? super E, Integer> destination) {
        Iterator<E> iterator = this.iterator();
        while (iterator.hasNext()) {
            E iter = iterator.next();
            destination.put(iter, this.getCount(iter));
        }
    }

    @Override
    public Map<E, Integer> toMap() {
        Map<E, Integer> map = new HashMap<>();
        for (E iter : this) {
            map.put(iter, this.getCount(iter));
        }
        return map;
    }


    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }


}



