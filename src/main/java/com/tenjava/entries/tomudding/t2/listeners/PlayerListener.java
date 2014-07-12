package com.tenjava.entries.tomudding.t2.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.tenjava.entries.tomudding.t2.TenJava;
import com.tenjava.entries.tomudding.t2.utils.ChatManager;

public class PlayerListener implements Listener {
	
	private TenJava tenJava;
	public PlayerListener(TenJava tenJava) {
		// TODO Auto-generated constructor stub
	}

	@EventHandler
	public void checkBlock(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
		
		if(block.getType() == Material.BURNING_FURNACE) {
			ChatManager.get().sendMessage(player, "Charging your Energy Orb...");
		}
	}
}
