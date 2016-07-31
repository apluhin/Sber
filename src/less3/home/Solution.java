package less3.home;

import java.io.IOException;
import java.util.*;

/**
 * Created by aleksejpluhin on 27.07.16.
 */
public class Solution {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> map = new TreeMap<>();
        while (scanner.hasNext()) {
            String line = scanner.next().toLowerCase();
            String[] word = line.split(" ");
            for (String s : word) {
                if (map.get(s) == null) {
                    map.put(s, 1);
                } else {
                    map.put(s, map.get(s) + 1);
                }
            }
        }


        int max = Integer.MIN_VALUE;
        for (Integer integer : map.values()) {
            if (integer > max) {
                max = integer;
            }
        }


        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(max)) {
                System.out.println(entry.getKey());
            }

        }






    }





}

