package less7.home.classloadeer;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class BrowserClassLoader extends URLClassLoader {

    private final String pluginName;
    private final String pluginClassName;

    public BrowserClassLoader(String pluginRootDirectory, String pluginName, String pluginClassName) throws MalformedURLException {
        super(new URL[]{new URL("file:" + pluginRootDirectory + pluginName + "/")});
        URL url = new URL("file:" + pluginRootDirectory + pluginName + "/");
        System.out.println(url);
        this.pluginName = pluginName;
        this.pluginClassName = pluginClassName;
    }


    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {

        if (name.startsWith("java") || name.endsWith("Plugin")) {
            return super.loadClass(name);
        }
        try {
            //Поменять местами return и все загрузят один browserClass
            return findClass(name);
        } catch (ClassNotFoundException e) {
            return super.loadClass(name);
        }

    }
}
