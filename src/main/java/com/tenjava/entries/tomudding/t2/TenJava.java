package com.tenjava.entries.tomudding.t2;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.tenjava.entries.tomudding.t2.listeners.PlayerListener;

public class TenJava extends JavaPlugin {

    public static TenJava instance;
    private final PlayerListener listener1 = new PlayerListener(this);
    
    public void onEnable() {
        instance = this;
        
        // Register Events
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(this.listener1, this);
    }

    public void onDisable() {
    }
}
