package less3.home;


import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by aleksejpluhin on 28.07.16.
 */
public class Solution1 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Map<Integer, Integer> map = new TreeMap<>();
        while (n > 0) {
            String[] arr = scanner.nextLine().split(" ");

            if (arr.length == 1) {
                System.out.println(min(map));
            } else {
                int num = Integer.parseInt(arr[1]);
                if (map.get(num) != null) {
                    map.put(num, map.get(num) + 1);
                } else {
                    map.put(num, 1);
                }

            }


            n--;
        }


    }

    public static int min(Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
            if (integerIntegerEntry.getValue() > 0) {
                int value = integerIntegerEntry.getValue();
                map.put(integerIntegerEntry.getKey(), value - 1);
                if (map.get(integerIntegerEntry.getKey()) == 0) {
                    map.remove(integerIntegerEntry.getKey());
                }
                return integerIntegerEntry.getKey();
            }
        }
        return 0;
    }


}
