package net.mmgmc.eup;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class EUPUtilities 
{
	
	private Plugin plugin = Main.getPlugin(Main.class);
	
	private CommandSender sender;
	
	public EUPUtilities(CommandSender sender)
	{
		this.sender = sender;
	}
	
	public void sendInvalidPermMessage()
	{
		String pluginPrefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"));
		String permissionError = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("invalidPermission"));
		sender.sendMessage(pluginPrefix + " " + permissionError);
	}
}
