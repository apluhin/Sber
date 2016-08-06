package less4.home.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by aleksejpluhin on 30.07.16.
 */
public class CollectionUtils {

    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {

        destination.addAll(source);

    }


    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }


    public static <T> int indexOf(List<? extends T> source, T o) {
        return source.indexOf(o);
    }


    public static <T> List<T> limit(List<T> source, int size) {
        if (source.size() <= size) {
            return source;
        } else {
            return source.subList(0, size);
        }
    }


    public static <T> void add(List<? super T> source, T o) {
        source.add(o);
    }


    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }


    public static <T> boolean containsAll(List<? extends T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }


    public static <T> boolean containsAny(List<? extends T> c1, List<? extends T> c2) {
        for (Object o : c2) {
            if (c1.contains(o)) {
                return true;
            }
        }
        return false;
    }


    public static <T> List<T> range(List<T> list, T min, T max) {
        List<T> temp = new ArrayList<>(list);
        Collections.sort(temp, (o1, o2) -> Integer.compare(o1.hashCode(), o2.hashCode()));
        if (temp.indexOf(min) < temp.indexOf(max)) {
            return temp.subList(temp.indexOf(min), temp.indexOf(max));
        } else {
            return newArrayList();
        }
    }


    public static <T> List<T> range(List<T> list, T min, T max, Comparator<? super T> comparator) {
        List<T> temp = new ArrayList<>(list);
        Collections.sort(list, comparator);
        if (temp.indexOf(min) < temp.indexOf(max)) {
            return temp.subList(temp.indexOf(min), temp.indexOf(max));
        } else {
            return newArrayList();
        }
    }

}