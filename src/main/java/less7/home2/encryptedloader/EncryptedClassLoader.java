package less7.home2.encryptedloader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EncryptedClassLoader extends ClassLoader {

    private final String key;
    private final File dir;

    public EncryptedClassLoader(String key, File dir, ClassLoader parent) {

        super(parent);
        this.key = key;
        this.dir = dir;

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {

            byte[] b = new Encrypter(new File(dir.getPath()), key).decript(name);
            Method m = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
            m.setAccessible(true);
            Class a = (Class<?>) m.invoke(this, name.substring(0, name.lastIndexOf(".")), b, 0, b.length);
            return a;


        } catch (IOException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("Error during load find class", e);
        }

    }

    public Class<?> loadClassFromFile(String name) throws ClassNotFoundException {
        return loadClass(name);
    }
}
