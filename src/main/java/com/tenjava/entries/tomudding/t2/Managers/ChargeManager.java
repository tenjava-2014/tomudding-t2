package com.tenjava.entries.tomudding.t2.managers;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import com.tenjava.entries.tomudding.t2.TenJava;

public class ChargeManager {
	
	static ChargeManager instance = new ChargeManager();
	private TenJava tenJava;
	  
	FileConfiguration data;
	File dfile;

	public static ChargeManager getInstance() {
	  return instance;
	}

	public Plugin getPlugin() {
	  return this.tenJava;
	}

	/*
	 * Settingup the Data.YML to save Player' there Charges
	 */
	public void setup(TenJava tenJava) {
	  this.dfile = new File(tenJava.getDataFolder(), "data.yml");

	  if (!this.dfile.exists()) {
	    try {
	      this.dfile.createNewFile();
	      Bukkit.getServer().getLogger().info(ChatColor.GREEN + "Successfully created data.yml");
	    } catch (IOException e) {
	      Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create data.yml!");
	    }
	  }
	  this.data = YamlConfiguration.loadConfiguration(this.dfile);
	}

	public FileConfiguration getData() {
	  return this.data;
	}

	public void saveData() {
	  try {
	    this.data.save(this.dfile);
	  } catch (IOException e) {
	    Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save data.yml!");
	  }
	}
	  
	public void reloadData() {
	  this.data = YamlConfiguration.loadConfiguration(this.dfile);
	}

	public PluginDescriptionFile getDesc() {
	  return tenJava.getDescription();
	}

	/*
	 * Getting the Charge from a Player
	 */
	public int getCharge(Player player) {
	  String p = player.getName();
	  return this.data.getInt(p);
	}
	
	/*
	 * Set the Charge of a Player
	 */
	public void setCharge(Player player, int i) {
	  String p = player.getName();
	  this.data.set(p, i);
	  saveData();
	}
	
	/*
	 * Add 'X' amount to the Charge of a Player
	 */
	public void addCharge(Player player, int i) {
	  String p = player.getName();
	  int o = this.getCharge(player);
	  if(o + i > 100) {
		this.data.set(p, 100);
	  } else {
	    this.data.set(p, o + i);
	  }
	  saveData();
	}
	
	
	/*
	 * Remove 'X' amount to the Charge of a Player
	 */
	public void removeCharge(Player player, int i) {
	  String p = player.getName();
	  int o = this.getCharge(player);
	  if(o - i < 0) {
		this.data.set(p, 0);
	  } else {
	    this.data.set(p, o - i);
	  }
	  saveData();
	}
}
