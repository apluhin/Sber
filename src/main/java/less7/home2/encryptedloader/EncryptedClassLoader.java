package less7.home2.encryptedloader;

import java.io.File;

public class EncryptedClassLoader extends ClassLoader {

    private final String key;
    private final File dir;
    private Encrypter encrypter;

    public EncryptedClassLoader(String key, File dir, ClassLoader parent, Encrypter encrypter) {

        super(parent);
        this.key = key;
        this.dir = dir;
        this.encrypter = encrypter;

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File(dir + "/" + name);
        byte[] b = encrypter.decript(file, key);
        return defineClass(name.substring(0, name.lastIndexOf(".")), b, 0, b.length);
    }

    public Class<?> loadClassFromFile(String name) throws ClassNotFoundException {
        return loadClass(name);
    }


}
