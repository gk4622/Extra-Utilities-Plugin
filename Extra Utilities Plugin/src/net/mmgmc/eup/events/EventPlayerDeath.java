package net.mmgmc.eup.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

import net.mmgmc.eup.Main;

public class EventPlayerDeath implements Listener
{
	private Plugin plugin = Main.getPlugin(Main.class);
	private boolean isLoggingEnabled = plugin.getConfig().getBoolean("trackDeaths");
	
	@EventHandler
	public void PlayerDeathE(PlayerDeathEvent e)
	{
		Player p = e.getEntity().getPlayer();
		if(isLoggingEnabled)
		{
			Bukkit.getConsoleSender().sendMessage(generateLogMsg(p));
		}
		else
		{
			return;
		}
	}
	
	private String generateLogMsg(Player p)
	{
		String pluginPrefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"));
		String logMsg = "";
		String playerDisplayName = p.getDisplayName();
		Location pLoc = p.getLocation();
		int pX = pLoc.getBlockX();
		int pY = pLoc.getBlockY();
		int pZ = pLoc.getBlockZ();
		String pWorld = pLoc.getWorld().getName();
			
		if(playerDisplayName.equals(p.getPlayerListName()))
		{
			logMsg = pluginPrefix + ChatColor.DARK_RED + " LOG:" + ChatColor.YELLOW + " PlayerDeathEvent" + ChatColor.GRAY + " - " + ChatColor.YELLOW + playerDisplayName 
					+ ChatColor.GRAY + " has died @" + ChatColor.YELLOW + " X:" + pX + " Y:" + pY + " Z:" + pZ + ChatColor.GRAY + " in " + ChatColor.YELLOW + pWorld;
		}
		else
		{
			logMsg = pluginPrefix + ChatColor.DARK_RED + " LOG:" + ChatColor.YELLOW + " PlayerDeathEvent" + ChatColor.GRAY + " - " + ChatColor.YELLOW + playerDisplayName 
					+ ChatColor.YELLOW + " (" + p.getPlayerListName() + ")" + ChatColor.GRAY + " has died @" + ChatColor.YELLOW + " X:" 
					+ pX + " Y:" + pY + " Z:" + pZ + ChatColor.GRAY + " in " + ChatColor.YELLOW + pWorld;
		}
		
		return logMsg;
	}
}
