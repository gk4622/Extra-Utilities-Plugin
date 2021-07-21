package net.mmgmc.eup.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import net.mmgmc.eup.Main;
import net.mmgmc.eup.EUPUtilities;

public class CommandReload implements CommandExecutor 
{
	private Plugin plugin = Main.getPlugin(Main.class);
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) 
	{
		
		EUPUtilities utils = new EUPUtilities(sender);
		
		if(sender.hasPermission("eup.reloadcfg"))
		{
			plugin.reloadConfig();
			sendSuccessMessage(sender);
		}
		else
		{
			utils.sendInvalidPermMessage();
		}
		
		return false;
	}
	
	private void sendSuccessMessage(CommandSender sender)
	{
		String pluginPrefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"));
		sender.sendMessage(pluginPrefix + ChatColor.GREEN + " Successfully reloaded config.yml!");
	}
	
}