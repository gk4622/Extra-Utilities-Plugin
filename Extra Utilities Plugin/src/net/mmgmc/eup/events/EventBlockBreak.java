package net.mmgmc.eup.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;

import net.mmgmc.eup.Main;

public class EventBlockBreak implements Listener
{
	
	private Plugin plugin = Main.getPlugin(Main.class);
	private boolean isLoggingEnabled = plugin.getConfig().getBoolean("trackBlocksBroken");
	
	@EventHandler
	public void blockBreakE(BlockBreakEvent e)
	{
		if(isLoggingEnabled)
		{
			Player p = e.getPlayer();
			Block block = e.getBlock();
			
			Bukkit.getConsoleSender().sendMessage(generateLogMsg(p, block));
		}
		else
		{
			return;
		}
	}
	
	private String generateLogMsg(Player p, Block block)
	{
		String pluginPrefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"));
		String logMsg = "";
		String playerDisplayName = p.getDisplayName();
		String blockName = block.getBlockData().getAsString();
		int bX = block.getX();
		int bY = block.getY();
		int bZ = block.getZ();
		String bWorld = block.getWorld().getName();
		
		if(playerDisplayName.equals(p.getPlayerListName()))
		{
			logMsg = pluginPrefix + ChatColor.DARK_RED + " LOG:" + ChatColor.YELLOW + " BlockBreakEvent" + ChatColor.GRAY + " - " + ChatColor.YELLOW + playerDisplayName + ChatColor.GRAY + " has broken " + ChatColor.YELLOW 
					+ blockName + ChatColor.GRAY + " @" + ChatColor.YELLOW + " X:" + bX + " Y:" + bY + " Z:" + bZ + ChatColor.GRAY + " in " + ChatColor.YELLOW + bWorld;
		}
		else
		{
			logMsg = pluginPrefix + ChatColor.DARK_RED + " LOG:" + ChatColor.YELLOW + " BlockBreakEvent" + ChatColor.GRAY + " - " + ChatColor.YELLOW + playerDisplayName + ChatColor.YELLOW 
					+ " (" + p.getPlayerListName() + ") " + ChatColor.GRAY + "has broken " + ChatColor.YELLOW + blockName + ChatColor.GRAY + " @" + ChatColor.YELLOW + " X:" + bX + " Y:" + bY 
					+ " Z:" + bZ + ChatColor.GRAY + " in " + ChatColor.YELLOW + bWorld;
		}
		
		return logMsg;
	}	
}
