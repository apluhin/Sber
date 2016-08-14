package less7.home2.encryptedloader;

import java.io.File;

public interface Encrypter {

    void encrypt(File file, String key);

    byte[] decript(File file, String key);
}
