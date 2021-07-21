package net.mmgmc.eup.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import net.mmgmc.eup.EUPUtilities;
import net.mmgmc.eup.Main;

public class CommandVote implements CommandExecutor 
{
	
private Plugin plugin = Main.getPlugin(Main.class);
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) 
	{
		File voteFile = new File(plugin.getDataFolder(), "vote.txt");
		try
		{
			if(sender.hasPermission("eup.vote"))
			{
				Scanner voteFileReader = new Scanner(voteFile);
				while(voteFileReader.hasNextLine())
				{
					String data = voteFileReader.nextLine();
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', data));
				}
				voteFileReader.close();
			}
			else
			{
				EUPUtilities utils = new EUPUtilities(sender);
				utils.sendInvalidPermMessage();
			}
		}
		catch (FileNotFoundException e)
		{
			sender.sendMessage(ChatColor.RED + "vote.txt cannot be found! Generating...");
			try 
			{
				File voteFileDir = voteFile.getParentFile();
				voteFileDir.mkdirs();
				voteFile.createNewFile();
				sender.sendMessage(ChatColor.GREEN + "vote.txt successfully created!");
			}
			catch (IOException e1) 
			{
				sender.sendMessage(ChatColor.DARK_RED + "Failed to create vote.txt!");
				e1.printStackTrace();
			}
		}
		return false;
	}
}
