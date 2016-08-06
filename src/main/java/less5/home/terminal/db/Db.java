package less5.home.terminal.db;

import less5.home.terminal.account.Account;

import java.util.HashMap;
import java.util.Map;

public class Db {


    private Map<Integer, Account> hashMap = new HashMap<>();


    private static Db instance;

    private Db() {
        hashMap.put(1234, new Account("Petya", 100002, 1111));
        hashMap.put(1235, new Account("Alexey", 0, 2222));
        hashMap.put(1236, new Account("Victor", 20, 3333));
        hashMap.put(1237, new Account("Vasiliy", 350, 4444));
    }

    public static Db getInstance() {
        if (instance == null) {
            instance = new Db();
        }
        return instance;
    }


    public Map<Integer, Account> getHashMap() {
        return hashMap;
    }
}
