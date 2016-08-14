package less7.home2.encryptedloader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EncrypterImpl implements Encrypter {
    private String key;
    private int position = 0;

    EncrypterImpl() {

    }

    //Метод гаммирования
    public void encrypt(File file, String key) {
        this.key = key;
        File output = new File(file.getPath().replace(".class", ".txt"));
        try {


            InputStream reader = new FileInputStream(file);
            OutputStream stream = new FileOutputStream(output, true);

            while (reader.available() > 0) {
                byte a = (byte) reader.read();
                byte nextByte = (byte) getNextByte();

                stream.write((byte) (a + nextByte));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error during encrypt file", e);
        }


    }

    private int getNextByte() {
        int b;
        try {
            b = key.charAt(position);
            position++;
        } catch (StringIndexOutOfBoundsException e) {
            position = 0;
            b = key.charAt(position);
        }
        return b;
    }

    public byte[] decript(File file, String key) {
        this.key = key;
        List<Integer> output = new ArrayList<>();
        try {
            InputStream reader = new FileInputStream(file);
            position = 0;
            while (reader.available() > 0) {
                byte b = (byte) reader.read();
                b -= (byte) getNextByte();
                output.add((int) b);

            }
        } catch (IOException e) {
            throw new RuntimeException("Error during decript", e);
        }

        byte[] arr = new byte[output.size()];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = output.get(i).byteValue();
        }

        return arr;


    }

}
