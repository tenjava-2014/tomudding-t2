package com.tenjava.entries.tomudding.t2.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import com.tenjava.entries.tomudding.t2.TenJava;
import com.tenjava.entries.tomudding.t2.utils.ChatManager;

public class PlayerListener implements Listener {
	
	private TenJava tenJava;
	public PlayerListener(TenJava tenJava) {
		this.tenJava = tenJava;
	}

	
	/*
	 * Player Join
	 */
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
	  Player player = event.getPlayer();
	  if(!(player.hasPlayedBefore())) {
		  tenJava.settings.setCharge(player, 100);
		  ChatManager.get().sendMessage(player, "Hay! Because this is your first join, we have charged your 'Energy Orb'");
		  int charge = tenJava.settings.getCharge(player);
		  ChatManager.get().sendMessage(player, "Current Charge: " + ChatColor.YELLOW + charge + "%");
	  }
	}
	
	// Checking for Energy Charger
	@EventHandler
	public void checkBlock(final PlayerMoveEvent event) {
		final Player player = event.getPlayer();
		final int i = 8;
		Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
		
		if(block.getType() == Material.BURNING_FURNACE) {
			if(!(player.getInventory().getItemInHand().getItemMeta().getDisplayName().equals("Eye of Ender")) && (player.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.RED + "Energy Orb")) && player.getInventory().getItemInHand().getType().equals(Material.EYE_OF_ENDER)) {
				if(!(tenJava.settings.getCharge(player) == 100)) {
				  ChatManager.get().sendMessage(player, "Charging your Energy Orb");
	    		  tenJava.settings.addCharge(player, i);
	    		  ChatManager.get().sendMessage(player, "Please move a bit to charge better");
	    		  event.setCancelled(true);
				} else {
				  event.setCancelled(false);
				  ChatManager.get().sendMessage(player, "Your Energy Orb is fully charged");
				}
			}
		}
	}
	
	
	// Disabling Interact with Orb
	@EventHandler
	public void stopInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		
		if((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			if(!(player.getInventory().getItemInHand().getItemMeta().getDisplayName().equals("Eye of Ender")) && (player.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.RED + "Energy Orb")) && player.getInventory().getItemInHand().getType().equals(Material.EYE_OF_ENDER)) {
				int charge = tenJava.settings.getCharge(player);
				ChatManager.get().sendMessage(player, "Current Charge: " + ChatColor.YELLOW + charge + "%");
				event.setCancelled(true);
			}
		}
	}
}
