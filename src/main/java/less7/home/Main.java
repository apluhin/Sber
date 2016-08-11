package less7.home;

import less7.home.sbt.Plugin;
import less7.home.sbt.PluginManager;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Plugin> plugins = new ArrayList<>();

        PluginManager pluginManager = new PluginManager("/Users/aleksejpluhin/IdeaProjects/Sber/src/main/java/less7/home/pluginRoot/");

        plugins.add(pluginManager.load("plugin1", "less7.home.sbt.Plugin1"));
        plugins.add(pluginManager.load("plugin2", "less7.home.sbt.Plugin2"));
        plugins.add(pluginManager.load("plugin3", "less7.home.sbt.Plugin3"));

        for (Plugin plugin : plugins) {
            plugin.doUsefull();
        }

    }
}
