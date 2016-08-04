package less5.home.store.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//change to interface
public class DbImpl implements Db {

    private final List<String> db = DbArraySingelton.getInstance().getArrayList();


    public void insert(String line) throws SQLException {
        try {
            if (Math.random() * 10 > 90) {
                throw new SQLException();
            }
            db.add(line);
        } catch (SQLException e) {
            throw new SQLException("Error during insert in db", e);
        }

    }

    public List<String> selectAll() throws SQLException {
        try {
            if (Math.random() * 10 > 90) {
                throw new SQLException();
            }
            return new ArrayList<>(db);
        } catch (SQLException e) {
            throw new SQLException("Error during select", e);
        }
    }

    @Override
    public void close() throws Exception {

    }
}
