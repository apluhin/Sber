package less5.home.store.impl;

import less5.home.store.Store;
import less5.home.store.exception.FileStoreException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStorage implements Store<String> {


    private final String path;

    public FileStorage(String path) {
        this.path = path;
    }

    //Файл создаться если не будет найден
    @Override
    public void save(String t) {
        try (FileWriter fileWriter = new FileWriter(new File(path), true)) {
            fileWriter.write(t + "\n");
        } catch (IOException e) {
            throw new FileStoreException("error during write file", e);
        }
    }

    @Override
    public List<String> getAll() {
        List<String> arrStr = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))))) {
            while (bufferedReader.ready()) {
                arrStr.add(bufferedReader.readLine());
            }
        } catch (IOException e) {
            throw new FileStoreException("error during read", e);
        }
        return arrStr;
    }
}
