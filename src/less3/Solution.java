package less3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by aleksejpluhin on 27.07.16.
 */
public class Solution {
    public static void main(String[] args) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while (bufferedReader.ready()) {
            builder.append(bufferedReader.readLine()).append(" ");
        }


        String line = builder.toString().toLowerCase();
        String[] word = line.split(" ");
        Map<String, Integer> map = new TreeMap<>();
        for (String s : word) {
            if (map.get(s) == null) {
                map.put(s, 1);
            } else {
                map.put(s, map.get(s) + 1);
            }
        }
        Set<Integer> set = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -1 * Integer.compare(o1,o2);
            }
        });
        set.addAll(map.values());
        set.remove(1);

        for (Integer current : set) {
            for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
                if (current.equals(stringIntegerEntry.getValue())) {
                    System.out.println(stringIntegerEntry.getKey().trim());
                    stringIntegerEntry.setValue(-1);

                }
            }
            break;

        }


    }


}

