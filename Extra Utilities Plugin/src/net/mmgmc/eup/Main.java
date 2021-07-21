package net.mmgmc.eup;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.mmgmc.eup.commands.CommandDiscord;
import net.mmgmc.eup.commands.CommandMOTD;
import net.mmgmc.eup.commands.CommandReload;
import net.mmgmc.eup.commands.CommandVote;
import net.mmgmc.eup.events.EventPlayerJoin;


public class Main extends JavaPlugin 
{
	
	@Override
	public void onEnable()
	{	
		initCommands();
		loadConfig();
		
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Extra-Utilities-Plugin has been enabled!");
	}
	
	@Override
	public void onDisable()
	{
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Extra-Utilities-Plugin has been disabled!");
	}
	
	public void loadConfig()
	{
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	private void initCommands()
	{
		getCommand("discord").setExecutor(new CommandDiscord());
		getCommand("vote").setExecutor(new CommandVote());
		getCommand("reloadeup").setExecutor(new CommandReload());
		getCommand("motd").setExecutor(new CommandMOTD());
	}
	
	private void initEvents()
	{
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new EventPlayerJoin(), this);
	}
}
