package tk.knowitallgn.ToS.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.knowitallgn.ToS.ToS;
import tk.knowitallgn.ToS.DataTypes.PlayerFile;

public class CommandIAgree implements CommandExecutor{

	private ToS plugin;
	
	public CommandIAgree(ToS plugin){
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(sender.hasPermission("ToS.command.iagree")){
			if(sender instanceof Player){
				String playerName = ((Player) sender).getName();
				PlayerFile playerFile = plugin.playerFiles.get(playerName);
				if(playerFile.hasRead()){
					if(playerFile.hasAgreed()){
						sender.sendMessage(plugin.config.getString("Messages.HasAgreed").replaceAll("(&([a-f0-9l-or]))", "\u00A7$2"));
					}else{
						plugin.playerFiles.put(playerName, playerFile.setAgreed(true));
						sender.sendMessage(plugin.config.getString("Messages.JustAgreed").replaceAll("(&([a-f0-9l-or]))", "\u00A7$2"));
					}
				}else{
					sender.sendMessage(plugin.config.getString("Messages.HasNotRead").replaceAll("(&([a-f0-9l-or]))", "\u00A7$2"));
				}
			}else{
				sender.sendMessage(ChatColor.RED + "You're the console, you don't have rules!");
			}
		}else{
			sender.sendMessage(ChatColor.RED + "You do not have permission to use that command.");
		}
		return true;
	}
}
