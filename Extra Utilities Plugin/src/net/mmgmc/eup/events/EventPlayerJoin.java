package net.mmgmc.eup.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.mmgmc.eup.commands.CommandMOTD;

public class EventPlayerJoin implements Listener 
{

	@EventHandler
	public void PlayerJoinE(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		displayMOTD(p);
	}
	
	private void displayMOTD(Player p)
	{
		CommandMOTD motd = new CommandMOTD();
		motd.getMOTD(p);
	}
}
