package less5.home.store.db;

import java.sql.SQLException;
import java.util.List;

public interface Db extends AutoCloseable {
    void insert(String line) throws SQLException;

    List<String> selectAll() throws SQLException;
}
