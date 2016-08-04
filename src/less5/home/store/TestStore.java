package less5.home.store;

import less5.home.store.impl.DataBaseStore;
import less5.home.store.impl.FileStorage;

import java.util.List;

public class TestStore {
    public static void main(String[] args) {
        Store<String> store = new FileStorage("/Users/aleksejpluhin/Desktop/5.txt");
        store.save("testString");
        store.save("testString2");
        List<String> allStr = store.getAll();
        for (String s : allStr) {
            System.out.println(s);
        }

        Store<String> store1 = new DataBaseStore();
        store1.save("1");
        store1.save("2");
        store1.save("3");
        List<String> stringList = store1.getAll();
        for (String s : stringList) {
            System.out.println(s);
        }

    }
}
