package less4.home.map;


import java.util.Map;
import java.util.TreeMap;

/**
 * Created by aleksejpluhin on 30.07.16.
 */
public class CountMapTest {
    public static void main(String[] args) {


        //Count map test

        CountMap<Double> map = new CountMapImpl<>();
        map.add(1d);
        map.add(2d);
        map.add(1d);
        map.add(1d);

        System.out.println("count 1d  " + map.getCount(1d)); // 3

        map.add(1d);

        System.out.println("count 1d  " + map.getCount(1d)); // 4
        System.out.println("count 3d  " + map.getCount(3d)); //0


        CountMap<String> mapString = new CountMapImpl<>();
        mapString.add("1");
        mapString.add("2");
        mapString.add("2");
        mapString.add("A");
        System.out.println("count 2  " + mapString.getCount("2")); // 2
        System.out.println("count 2 before remove " + mapString.remove("2"));   // 2


        CountMap<Object> objects = new CountMapImpl<>();
        System.out.println("empty objects size: " + objects.size()); // 0
        objects.addAll(mapString);
        System.out.println("objects after first add: " + objects.size()); // 2
        objects.addAll(map);
        System.out.println("objects after second add: " + objects.size()); // 4
        objects.addAll(map);


        Map<Object, Integer> objectIntegerMap = objects.toMap();
        System.out.println("----");
        for (Map.Entry<Object, Integer> entry : objectIntegerMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        Map<Double, Integer> doubleIntegerMap = new TreeMap<>();
        map.toMap(doubleIntegerMap);
        System.out.println("-----");
        for (Map.Entry<Double, Integer> entry : doubleIntegerMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }


    }
}
