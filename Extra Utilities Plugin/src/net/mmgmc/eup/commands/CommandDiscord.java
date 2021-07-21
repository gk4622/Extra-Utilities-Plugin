package net.mmgmc.eup.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import net.mmgmc.eup.EUPUtilities;
import net.mmgmc.eup.Main;

public class CommandDiscord implements CommandExecutor 
{
	
	private Plugin plugin = Main.getPlugin(Main.class);
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) 
	{
		
		if(sender.hasPermission("eup.discord"))
		{
			String discordMessage = plugin.getConfig().getString("discordMessage");
			String discordLink = plugin.getConfig().getString("discordLink");	
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', discordMessage) + " " + ChatColor.translateAlternateColorCodes('&', discordLink));
		}
		else
		{
			EUPUtilities utils = new EUPUtilities(sender);
			utils.sendInvalidPermMessage();
		}
		
		return false;
	}

}
