package com.tenjava.entries.tomudding.t2.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatManager {
	
  private static ChatManager ins = new ChatManager();

  public String prefix = (ChatColor.GRAY + "[" + ChatColor.RED + "Energy" + ChatColor.GRAY + "] ");

  public static ChatManager get() {
    return ins;
  }

  public void log(String s) {
    Bukkit.getConsoleSender().sendMessage(this.prefix + ChatColor.translateAlternateColorCodes('&', s));
  }

  public void sendMessage(Player player, String s) {
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.prefix + s));
  }
}
