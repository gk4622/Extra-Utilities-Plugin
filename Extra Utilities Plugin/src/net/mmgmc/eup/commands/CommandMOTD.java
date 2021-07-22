package net.mmgmc.eup.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.clip.placeholderapi.PlaceholderAPI;
import net.mmgmc.eup.EUPUtilities;
import net.mmgmc.eup.Main;

public class CommandMOTD implements CommandExecutor 
{
	
	private Plugin plugin = Main.getPlugin(Main.class);
	private String pluginPrefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"));	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) 
	{
		if(sender instanceof Player)
		{
			getMOTD(sender);
			return true;
		}
		else
		{
			sender.sendMessage(pluginPrefix + ChatColor.RED + " You must be a player to execute this command!");
			return true;
		}		
		
	}

	public void getMOTD(CommandSender sender)
	{
		File motdFile = new File(plugin.getDataFolder(), "motd.txt");
		try
		{
			if(sender.hasPermission("eup.motd"))
			{
				Scanner motdFileReader = new Scanner(motdFile);
				while(motdFileReader.hasNextLine())
				{
					String data = motdFileReader.nextLine();
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders((OfflinePlayer) sender, data)));
				}
				motdFileReader.close();
			}
			else
			{
				EUPUtilities utils = new EUPUtilities(sender);
				utils.sendInvalidPermMessage();
			}
		}
		catch (FileNotFoundException e)
		{
			sender.sendMessage(ChatColor.RED + "motd.txt cannot be found! Generating...");
			try 
			{
				File voteFileDir = motdFile.getParentFile();
				voteFileDir.mkdirs();
				motdFile.createNewFile();
				sender.sendMessage(ChatColor.GREEN + "motd.txt successfully created!");
			}
			catch (IOException e1) 
			{
				sender.sendMessage(ChatColor.DARK_RED + "Failed to create motd.txt!");
				e1.printStackTrace();
			}
		}
	}
}
