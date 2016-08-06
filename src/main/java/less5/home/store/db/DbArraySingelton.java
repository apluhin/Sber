package less5.home.store.db;

import java.util.ArrayList;

public class DbArraySingelton {


    ArrayList<String> arrayList = new ArrayList<>();


    private static DbArraySingelton instance;

    private DbArraySingelton() {
    }

    public static DbArraySingelton getInstance() {
        if (instance == null) {
            instance = new DbArraySingelton();
        }
        return instance;
    }

    public ArrayList<String> getArrayList() {
        return arrayList;
    }
}
