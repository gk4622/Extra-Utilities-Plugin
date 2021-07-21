package net.mmgmc.eup.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import net.mmgmc.eup.EUPUtilities;
import net.mmgmc.eup.Main;

public class CommandEUP implements CommandExecutor 
{
	
	private Plugin plugin = Main.getPlugin(Main.class);
	private String pluginPrefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"));	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) 
	{
		EUPUtilities utils = new EUPUtilities(sender);
		if(args.length == 0 && sender.hasPermission("eup.eup"))
		{
			aboutPlugin(sender);
		}
		else if(args[0].equalsIgnoreCase("help") && sender.hasPermission("eup.help"))
		{
			displayCommands(sender);
		}
		else if(args[0].equalsIgnoreCase("reload") && sender.hasPermission("eup.reload"))
		{
			plugin.reloadConfig();
			sender.sendMessage(pluginPrefix + ChatColor.GREEN + " Successfully reloaded config.yml!");
		}
		else
		{
			utils.sendInvalidPermMessage();
		}
		return false;
	}
	
	private void aboutPlugin(CommandSender sender)
	{
		String pluginVersion = plugin.getDescription().getVersion();
		sender.sendMessage(pluginPrefix + ChatColor.GREEN + " Extra-Utilities-Plugin v" + pluginVersion);
	}
	
	private void displayCommands(CommandSender sender)
	{
		sender.sendMessage(pluginPrefix + ChatColor.GREEN + " Help menu");
	}

}
