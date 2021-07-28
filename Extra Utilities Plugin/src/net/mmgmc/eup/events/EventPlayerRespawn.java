package net.mmgmc.eup.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;

import net.mmgmc.eup.Main;

public class EventPlayerRespawn implements Listener
{

	private Plugin plugin = Main.getPlugin(Main.class);
	private boolean isLoggingEnabled = plugin.getConfig().getBoolean("trackRespawns");
	
	@EventHandler
	public void PlayerRespawnE(PlayerRespawnEvent e)
	{
		Player p = e.getPlayer();
		
		if(isLoggingEnabled)
		{
			Location respawnLoc = e.getRespawnLocation();
			Bukkit.getConsoleSender().sendMessage(generateLogMsg(p, respawnLoc));
		}
		else
		{
			return;
		}
	}
	
	private String generateLogMsg(Player p, Location respawnLoc)
	{
		String pluginPrefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"));
		String logMsg = "";
		String playerDisplayName = p.getDisplayName();
		int pX = respawnLoc.getBlockX();
		int pY = respawnLoc.getBlockY();
		int pZ = respawnLoc.getBlockZ();
		String pWorld = respawnLoc.getWorld().getName();
			
		if(playerDisplayName.equals(p.getPlayerListName()))
		{
			logMsg = pluginPrefix + ChatColor.DARK_RED + " LOG:" + ChatColor.YELLOW + " PlayerRespawnEvent" + ChatColor.GRAY + " - " + ChatColor.YELLOW + playerDisplayName 
					+ ChatColor.GRAY + " has respawned @" + ChatColor.YELLOW + " X:" + pX + " Y:" + pY + " Z:" + pZ + ChatColor.GRAY + " in " + ChatColor.YELLOW + pWorld;
		}
		else
		{
			logMsg = pluginPrefix + ChatColor.DARK_RED + " LOG:" + ChatColor.YELLOW + " PlayerRespawnEvent" + ChatColor.GRAY + " - " + ChatColor.YELLOW + playerDisplayName 
					+ ChatColor.YELLOW + " (" + p.getPlayerListName() + ")" + ChatColor.GRAY + " has respawned @" + ChatColor.YELLOW + " X:" 
					+ pX + " Y:" + pY + " Z:" + pZ + ChatColor.GRAY + " in " + ChatColor.YELLOW + pWorld;
		}
		
		return logMsg;
	}	
}
