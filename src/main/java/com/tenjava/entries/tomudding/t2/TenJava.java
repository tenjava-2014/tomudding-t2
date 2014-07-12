package com.tenjava.entries.tomudding.t2;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.tenjava.entries.tomudding.t2.listeners.PlayerListener;
import com.tenjava.entries.tomudding.t2.managers.ChargeManager;

public class TenJava extends JavaPlugin {

    public static TenJava instance;
    private final PlayerListener listener1 = new PlayerListener(this);
	public ChargeManager settings = ChargeManager.getInstance();
    
    public void onEnable() {
    	saveDefaultConfig();
    	
        instance = this;
        
        // Register Events
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(this.listener1, this);
        
        // Loading Recipe
        orbRecipe();
        
        // Setting Up ChargeManager
	    this.settings.setup(this);
	    this.settings.saveData();
    }

    public void onDisable() {
    }
    
    public void orbRecipe() {
  	  // Creating Energy Orb
  	  ItemStack orb = new ItemStack(Material.EYE_OF_ENDER, 1);
  	  ItemMeta meta = orb.getItemMeta();
  	  meta.setDisplayName(ChatColor.RED + "Energy Orb");
  	  meta.setLore(Arrays.asList("This Energy Orb", "can be used for", "some special things"));
  	  
  	  orb.setItemMeta(meta);
  	  
  	  // Creating the new Recipe
  	  ShapedRecipe orbRecipe = new ShapedRecipe(orb);
  	  orbRecipe.shape("!!!","?@?","!!!");
  	  orbRecipe.setIngredient('@', Material.EYE_OF_ENDER);
  	  orbRecipe.setIngredient('!', Material.FURNACE);
  	  orbRecipe.setIngredient('?', Material.MAGMA_CREAM);
  	  Bukkit.getServer().addRecipe(orbRecipe);
    }
}
