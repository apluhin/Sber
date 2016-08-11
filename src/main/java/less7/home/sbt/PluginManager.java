package less7.home.sbt;

import less7.home.classloadeer.BrowserClassLoader;

import java.net.MalformedURLException;

public class PluginManager {
    private final String pluginRootDirectory;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    public Plugin load(String pluginName, String pluginClassName) {
      
        try {
            return (Plugin) new BrowserClassLoader(pluginRootDirectory, pluginName, pluginClassName).loadClass(pluginClassName).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | MalformedURLException e) {
            throw new RuntimeException("Error during load plugin", e);
        }

    }
}

