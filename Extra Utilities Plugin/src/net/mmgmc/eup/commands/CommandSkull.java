package net.mmgmc.eup.commands;

import java.lang.reflect.Field;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.mmgmc.eup.EUPUtilities;
import net.mmgmc.eup.Main;

public class CommandSkull implements CommandExecutor
{
	private Plugin plugin = Main.getPlugin(Main.class);
	private String pluginPrefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"));	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) 
	{
		EUPUtilities utils = new EUPUtilities(sender);
		
		if(!(sender instanceof Player))
		{
			sender.sendMessage(pluginPrefix + ChatColor.RED + " You must be a player to execute this command!");
			return true;
		}
		else if(args.length == 0)
		{
			String invalidSyntaxMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("invalidSyntax"));
			sender.sendMessage(pluginPrefix + " " + invalidSyntaxMessage);
			return true;
		}
		else
		{
			if(sender.hasPermission("eup.skull"))
			{
				Player p = (Player) sender;
				String skullURL = args[0];
				skullURL = skullURL.trim();
				
				ItemStack skull = createCustomSkull(skullURL);
				
				p.getInventory().addItem(skull);
				p.sendMessage(pluginPrefix + ChatColor.GREEN + " success!");
				
				return true;
			}
			else
			{
				utils.sendInvalidPermMessage();
				return true;
			}
			
		}
	}
	
	private ItemStack createCustomSkull(String skullURL)
	{
		ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
		
		if(skullURL == null)
		{
			return skull;
		}
		
		ItemMeta skullMeta = skull.getItemMeta();
		GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		byte[] encodedData = Base64Coder.encodeString("{textures:{SKIN:{url:\"" + skullURL + "\"}}}").getBytes();
		
		profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
		Field profileField = null;
		
		try 
		{
			profileField = skullMeta.getClass().getDeclaredField("profile");
		} 
		catch (NoSuchFieldException | SecurityException e) 
		{
			e.printStackTrace();
		}
		
		profileField.setAccessible(true);
		
		try 
		{
			profileField.set(skullMeta, profile);
		} 
		catch (IllegalArgumentException | IllegalAccessException e) 
		{
			e.printStackTrace();
		}
		
		skull.setItemMeta(skullMeta);
		
		return skull;
	}

}
