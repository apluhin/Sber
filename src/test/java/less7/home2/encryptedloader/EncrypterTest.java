package less7.home2.encryptedloader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;


public class EncrypterTest {

    Encrypter encrypter;
    File dir;
    String compileClassName;
    String encryptFileName;
    String key = "password";
    String key1 = "PASSWORD";
    File compileFile;
    File encryptedFile;


    @Before
    public void setUp() {
        dir = new File("src/test/resources/HelloWorldComplile");
        compileClassName = "HelloWorld.class";
        encryptFileName = "HelloWorld.txt";
        compileFile = new File(dir + "/" + compileClassName);
        encryptedFile = new File(dir + "/" + encryptFileName);

        if (encryptedFile.exists()) {
            encryptedFile.delete();
        }
    }


    @Test(expected = IllegalArgumentException.class)
    public void testWrongKey() throws IOException {
        encrypter = new EncrypterImpl();
        byte[] a = Files.readAllBytes(compileFile.toPath());
        encrypter.encrypt(compileFile, key);
        byte[] b = encrypter.decript(encryptedFile, key1);
        if (!Arrays.equals(a, b)) {
            throw new IllegalArgumentException();
        }
    }

    @Test
    public void testRightKey() throws IOException {
        encrypter = new EncrypterImpl();
        byte[] a = Files.readAllBytes(compileFile.toPath());
        encrypter.encrypt(compileFile, key);
        byte[] b = encrypter.decript(encryptedFile, key);
        Assert.assertArrayEquals(a, b);

    }


}