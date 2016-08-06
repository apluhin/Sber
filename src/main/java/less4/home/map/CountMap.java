package less4.home.map;

import java.util.Map;

/**
 * Created by aleksejpluhin on 30.07.16.
 */
public interface CountMap<E> extends Iterable<E> {

    void add(E o);

    int getCount(E o);

    //current value
    int remove(E o);

    int size();

    <T extends E> void addAll(CountMap<T> source);

    void toMap(Map<? super E, Integer> destination);

    Map<E, Integer> toMap();


}
