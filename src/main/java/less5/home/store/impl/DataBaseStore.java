package less5.home.store.impl;


import less5.home.store.Store;
import less5.home.store.db.Db;
import less5.home.store.db.DbImpl;
import less5.home.store.exception.DataBaseStoreException;

import java.sql.SQLException;
import java.util.List;

public class DataBaseStore implements Store<String> {

    @Override
    public void save(String t) {
        try (Db db = new DbImpl()) {
            db.insert(t);
        } catch (SQLException e) {
            throw new DataBaseStoreException("Error during save in db", e);
        } catch (Exception e) {
            throw new DataBaseStoreException("Error during close", e);
        }
    }

    @Override
    public List<String> getAll() {
        List<String> list;
        try (Db db = new DbImpl()) {
            list = db.selectAll();
        } catch (SQLException e) {
            throw new DataBaseStoreException("Error during get from db", e);
        } catch (Exception e) {
            throw new DataBaseStoreException("Error during close", e);
        }
        return list;
    }
}
