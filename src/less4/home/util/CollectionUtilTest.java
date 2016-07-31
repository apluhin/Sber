package less4.home.util;


import java.util.*;

/**
 * Created by aleksejpluhin on 31.07.16.
 */
public class CollectionUtilTest {
    public static void main(String[] args) {

        List<String> stringList = CollectionUtils.newArrayList();
        List<Double> doubleList = CollectionUtils.newArrayList();

        stringList.add("alex");
        stringList.add("nina");
        stringList.add("petya");

        doubleList.add(1d);
        doubleList.add(2d);
        doubleList.add(3d);

        List<Number> numberList = CollectionUtils.newArrayList();

        CollectionUtils.addAll(doubleList, numberList);

        System.out.println(numberList.size()); //3

        List<Object> objectList = CollectionUtils.newArrayList();

        CollectionUtils.addAll(stringList, objectList);
        CollectionUtils.addAll(doubleList, objectList);

        System.out.println(objectList.size()); // 6

        System.out.println("index of: " + CollectionUtils.indexOf(objectList, "nina"));

        List<Object> listLimit = CollectionUtils.limit(objectList, 2);

        System.out.println("limit list: " + listLimit.size()); // 2

        CollectionUtils.add(doubleList, 2d);
        CollectionUtils.add(doubleList, 3d);

        System.out.println("Size double list 5 = " + doubleList.size());

        CollectionUtils.removeAll(objectList, doubleList);

        System.out.println("size 6 - 3" + objectList.size()); // 6 - 3

        System.out.println("Contain all stringList: " + CollectionUtils.containsAll(objectList, stringList));

        System.out.println("Contain any double: " + CollectionUtils.containsAny(objectList, doubleList));
        CollectionUtils.add(objectList, 1d);
        System.out.println("Contain any double: " + CollectionUtils.containsAny(objectList, doubleList));

        List<Double> range = CollectionUtils.range(doubleList, 1d, 3d);
        for (Double aDouble : range) {
            System.out.print(aDouble + " ");
        }
        List<String> range1 = CollectionUtils.range(stringList, "alex", "petya", new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.max(o1.length(), o2.length());
            }
        });
        for (String s : range1) {
            System.out.println(s + " ");
        }
    }
}
