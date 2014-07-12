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

import com.tenjava.entries.tomudding.t2.commands.Command;
import com.tenjava.entries.tomudding.t2.listeners.FurnaceListener;
import com.tenjava.entries.tomudding.t2.listeners.MinecartListener;
import com.tenjava.entries.tomudding.t2.listeners.PlayerListener;
import com.tenjava.entries.tomudding.t2.managers.ChargeManager;
import com.tenjava.entries.tomudding.t2.utils.ChatManager;

public class TenJava extends JavaPlugin {

    public static TenJava instance;
    private final PlayerListener listener1 = new PlayerListener(this);
    public ChargeManager settings = ChargeManager.getInstance();
    public int ChargeAmount;
    public int FurnaceAmount;
    
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
	    
	// Loading Config
	loadConfigVariables();
	    
	ChatManager.get().log("ten.java contest plugin by Tomudding is enabled");
    }

    public void onDisable() {
	    ChatManager.get().log("ten.java contest plugin by Tomudding is disabled");
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
    
	private void loadConfigVariables() {
	  this.ChargeAmount = getConfig().getInt("Charge-Units");
	  this.FurnaceAmount = getConfig().getInt("Furnace-Units");
	}
}
