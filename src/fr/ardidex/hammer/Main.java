package fr.ardidex.hammer;

import fr.ardidex.hammer.Events.BreakEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new BreakEvent(), this);
    }
}
