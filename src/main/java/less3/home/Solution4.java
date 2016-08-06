package less3.home;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by aleksejpluhin on 30.07.16.
 */
public class Solution4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Map<String, ArrayList<Integer>> map = new TreeMap<>();
        while (n != 0) {
            String[] arr = scanner.nextLine().split(" ");
            if (map.get(arr[0]) != null) {
                ArrayList<Integer> list = map.get(arr[0]);
                list.add(Integer.parseInt(arr[1]));
                map.put(arr[0], list);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                map.put(arr[0], list);
            }
            n--;
        }

        for (Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() == 0) {
                continue;
            }
            int av = 0;
            for (int i = 0; i < entry.getValue().size(); i++) {
                av += entry.getValue().get(i);
            }
            av = av / entry.getValue().size();
            System.out.println(entry.getKey() + " " + av);
        }

    }
}
