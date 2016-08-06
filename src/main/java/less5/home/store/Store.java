package less5.home.store;

import java.util.List;

public interface Store<String> {
    void save(String t);


    List<String> getAll();
}
