package less3.home;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * Created by aleksejpluhin on 29.07.16.
 */
public class Solution3 {
    public static void main(String[] args) throws IOException {


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String line = bufferedReader.readLine();

        Set<String> set = new HashSet<>();
        int count = 0;
        for (int i = 0; i < line.length(); i++) {
            for (int j = i + 1; j < line.length() + 1; j++) {
                if (set.add(line.substring(i, j))) {
                    count++;
                }
            }
        }
        System.out.print(count);
        Map<String, Integer> map = new TreeMap<>();
        SortedSet<Map.Entry<String, Integer>> sortedSet = new TreeSet<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                int a = -1 * o1.getValue().compareTo(o2.getValue());
                if(a != 0) {
                    return a;
                } else {
                    return o1.getKey().compareTo(o2.getKey());
                }

            }
        });

            map.put("1", 1);
            map.put("2", 2);
            map.put("3", 33);
            map.put("4", 110);
            map.put("5", 20);
            map.put("6", 1);
            map.put("7", 1);
            map.put("8", 1);
        sortedSet.addAll(map.entrySet());
        System.out.println(1);

    }


}
