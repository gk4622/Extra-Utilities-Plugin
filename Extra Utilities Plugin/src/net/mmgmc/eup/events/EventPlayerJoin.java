package net.mmgmc.eup.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import net.mmgmc.eup.Main;
import net.mmgmc.eup.commands.CommandMOTD;

public class EventPlayerJoin implements Listener 
{
	private Plugin plugin = Main.getPlugin(Main.class);
	private boolean isLoggingEnabled = plugin.getConfig().getBoolean("trackLogins");

	@EventHandler
	public void PlayerJoinE(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		displayMOTD(p);
		
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
		Location pLoc = p.getLocation();
		int pX = pLoc.getBlockX();
		int pY = pLoc.getBlockY();
		int pZ = pLoc.getBlockZ();
		String pWorld = pLoc.getWorld().getName();
		
		if(playerDisplayName.equals(p.getPlayerListName()))
		{
			logMsg = pluginPrefix + ChatColor.DARK_RED + " LOG:" + ChatColor.YELLOW + " PlayerJoinEvent" + ChatColor.GRAY + " - " + ChatColor.YELLOW + playerDisplayName + " [" + playerIPAddress + "]" 
					+ ChatColor.GRAY + " has connected @" + ChatColor.YELLOW + " X:" + pX + " Y:" + pY + " Z:" + pZ + ChatColor.GRAY + " in " + ChatColor.YELLOW + pWorld;
		}
		else
		{
			logMsg = pluginPrefix + ChatColor.DARK_RED + " LOG:" + ChatColor.YELLOW + " PlayerJoinEvent" + ChatColor.GRAY + " - " + ChatColor.YELLOW + playerDisplayName 
					+ ChatColor.YELLOW + " (" + p.getPlayerListName() + ") [" + playerIPAddress + "]" + ChatColor.GRAY + " has connected @" + ChatColor.YELLOW + " X:" 
					+ pX + " Y:" + pY + " Z:" + pZ + ChatColor.GRAY + " in " + ChatColor.YELLOW + pWorld;
		}
		
		return logMsg;
	}	
	
	private void displayMOTD(Player p)
	{
		CommandMOTD motd = new CommandMOTD();
		motd.getMOTD(p, true);
	}
}
