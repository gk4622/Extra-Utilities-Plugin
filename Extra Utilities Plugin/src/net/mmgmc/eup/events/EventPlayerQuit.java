package net.mmgmc.eup.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import net.mmgmc.eup.Main;

public class EventPlayerQuit implements Listener
{
	private Plugin plugin = Main.getPlugin(Main.class);
	private boolean isLoggingEnabled = plugin.getConfig().getBoolean("trackDisconnects");

	@EventHandler
	public void PlayerQuitE(PlayerQuitEvent e)
	{
		Player p = e.getPlayer();
		
		if(isLoggingEnabled)
		{
			Bukkit.getConsoleSender().sendMessage(generateLogMsg(p));
		}
		
	}
	
	private String generateLogMsg(Player p)
	{
		String pluginPrefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"));
		String logMsg = "";
		String playerDisplayName = p.getDisplayName();
		String playerIPAddress = p.getAddress().getAddress().toString() + ":" + p.getAddress().getPort();
		int pX = p.getLocation().getBlockX();
		int pY = p.getLocation().getBlockY();
		int pZ = p.getLocation().getBlockZ();
		String pWorld = p.getLocation().getWorld().getName();
		
		if(playerDisplayName.equals(p.getPlayerListName()))
		{
			logMsg = pluginPrefix + ChatColor.DARK_RED + " LOG:" + ChatColor.YELLOW + " PlayerQuitEvent" + ChatColor.GRAY + " - " + ChatColor.YELLOW + playerDisplayName + " [" + playerIPAddress + "]" 
					+ ChatColor.GRAY + " has disconnected @" + ChatColor.YELLOW + " X:" + pX + " Y:" + pY + " Z:" + pZ + ChatColor.GRAY + " in " + ChatColor.YELLOW + pWorld;
		}
		else
		{
			logMsg = pluginPrefix + ChatColor.DARK_RED + " LOG:" + ChatColor.YELLOW + " PlayerQuitEvent" + ChatColor.GRAY + " - " + ChatColor.YELLOW + playerDisplayName 
					+ ChatColor.YELLOW + " (" + p.getPlayerListName() + ") [" + playerIPAddress + "]" + ChatColor.GRAY + " has disconnected @" + ChatColor.YELLOW + " X:" 
					+ pX + " Y:" + pY + " Z:" + pZ + ChatColor.GRAY + " in " + ChatColor.YELLOW + pWorld;
		}
		
		return logMsg;
	}	
}
