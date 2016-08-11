package less7.home2.encryptedloader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Encrypter {
    File dir;
    String key;
    private int position = 0;

    public Encrypter(File dir, String key) {
        this.dir = dir;
        this.key = key;
    }

    public void encrypt(String name) throws IOException {
        File output = new File(dir.getPath() + "/" + name.replace(".class", ".txt"));

        InputStream reader = new FileInputStream(new File(dir + "/" + name));
        OutputStream stream = new FileOutputStream(output, true);

        while (reader.available() > 0) {
            byte a = (byte) reader.read();
            byte nextByte = (byte) getNextByte();

            stream.write((byte) (a + nextByte));
        }


    }

    public int getNextByte() {
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

    public byte[] decript(String name) throws IOException {
        List<Integer> output = new ArrayList<>();
        InputStream reader = new FileInputStream(dir + "/" + name);
        position = 0;
        while (reader.available() > 0) {
            byte b = (byte) reader.read();
            b -= (byte) getNextByte();
            output.add((int) b);

        }

        byte[] arr = new byte[output.size()];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = output.get(i).byteValue();
        }

        return arr;


    }


}
