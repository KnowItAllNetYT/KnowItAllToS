package tk.knowitallgn.ToS.Commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import tk.knowitallgn.ToS.ToS;

public class CommandToS implements CommandExecutor{

	private ToS plugin;
	
	public CommandToS(ToS plugin){
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(sender.hasPermission("ToS.command.tos")){
			if(args.length == 0){
				sender.sendMessage(ChatColor.BLUE + "ToS version " + plugin.getDescription().getVersion() + " by YoFuzzy3.");
				sender.sendMessage(ChatColor.GOLD + "/ToS reload " + ChatColor.GREEN + "Reload the config.yml and tasks.");
				sender.sendMessage(ChatColor.GOLD + "/ToS forcesave " + ChatColor.GREEN + "Forcesave PlayerFiles.ser.");
				sender.sendMessage(ChatColor.GOLD + "/ToS resetconfig " + ChatColor.GREEN + "Reset your config.yml.");
				sender.sendMessage(ChatColor.GOLD + "/ToS resetdata " + ChatColor.GREEN + "Reset all PlayerFiles.ser data.");
				sender.sendMessage(ChatColor.GOLD + "/ToS setread <player> <true/false> " + ChatColor.GREEN + "Set read status.");
				sender.sendMessage(ChatColor.GOLD + "/ToS setagreed <player> <true/false> " + ChatColor.GREEN + "Set agreed status.");
			}else if(args.length == 1){
				if(args[0].equalsIgnoreCase("reload")){
					plugin.stopTasks();
					plugin.reloadConfig();
					plugin.config = plugin.getConfig();
					plugin.startTasks();
					sender.sendMessage(ChatColor.GREEN + "The config.yml has been reloaded.");
				}else if(args[0].equalsIgnoreCase("forcesave")){
					plugin.saveData();
					sender.sendMessage(ChatColor.GREEN + "PlayerFiles.ser has been successfully forcesaved.");
				}else if(args[0].equalsIgnoreCase("resetconfig")){
					plugin.stopTasks();
					new File(plugin.getDataFolder(), "config.yml").delete();
					plugin.getConfig().options().copyDefaults(true);
					plugin.saveConfig();
					plugin.reloadConfig();
					plugin.config = plugin.getConfig();
					plugin.startTasks();
					sender.sendMessage(ChatColor.GREEN + "The config.yml has been successfully reset.");

				}else{
					sender.sendMessage(ChatColor.RED + "Type /ToS for help.");
				}
			}else if(args.length == 3){
				if(args[0].equals("setread")){
					if(plugin.playerFiles.containsKey(args[1])){
						if(args[2].equalsIgnoreCase("true") || args[2].equalsIgnoreCase("false")){
							plugin.playerFiles.put(args[1], plugin.playerFiles.get(args[1]).setRead(Boolean.valueOf(args[2])));
							sender.sendMessage(ChatColor.GREEN + "You have " + (Boolean.valueOf(args[2]) ? "read" : "unread") + " the rules for " + args[1] + ".");
						}else{
							sender.sendMessage(ChatColor.RED + "Type /ToS for help.");
						}
					}else{
						sender.sendMessage(ChatColor.RED + "That player does not exist");
					}
				}else if(args[0].equals("setagreed")){
					if(plugin.playerFiles.containsKey(args[1])){
						if(args[2].equalsIgnoreCase("true") || args[2].equalsIgnoreCase("false")){
							plugin.playerFiles.put(args[1], plugin.playerFiles.get(args[1]).setAgreed(Boolean.valueOf(args[2])));
							sender.sendMessage(ChatColor.GREEN + "You have " + (Boolean.valueOf(args[2]) ? "agreed" : "unagreed") + " to the rules for " + args[1] + ".");
						}else{
							sender.sendMessage(ChatColor.RED + "Type /ToS for help.");
						}
					}else{
						sender.sendMessage(ChatColor.RED + "That player does not exist");
					}
				}else{
					sender.sendMessage(ChatColor.RED + "Type /ToS for help.");
				}
			}else{
				sender.sendMessage(ChatColor.RED + "Type /ToS for help.");
			}
		}else{
			sender.sendMessage(ChatColor.RED + "You do not have permission to use that command.");
		}
		return true;
	}
}
