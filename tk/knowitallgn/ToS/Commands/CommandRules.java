package tk.knowitallgn.ToS.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.knowitallgn.ToS.ToS;
import tk.knowitallgn.ToS.DataTypes.PlayerFile;

public class CommandRules implements CommandExecutor{

	private ToS plugin;
	
	public CommandRules(ToS plugin){
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(sender.hasPermission("ToS.command.rules")){
			for(String rule : plugin.config.getStringList("Rules")){
				sender.sendMessage(rule.replaceAll("(&([a-f0-9l-or]))", "\u00A7$2"));
			}
			if(sender instanceof Player){
				String playerName = ((Player) sender).getName();
				PlayerFile playerFile = plugin.playerFiles.get(playerName);
				if(playerFile.hasAgreed()){
					sender.sendMessage(plugin.config.getString("Messages.HasAgreed").replaceAll("(&([a-f0-9l-or]))", "\u00A7$2"));
				}else{
					sender.sendMessage(plugin.config.getString("Messages.HasNotAgreed").replaceAll("(&([a-f0-9l-or]))", "\u00A7$2"));
				}
				if(!playerFile.hasRead()){
					plugin.playerFiles.put(playerName, playerFile.setRead(true));
				}
			}
		}else{
			sender.sendMessage(ChatColor.RED + "You do not have permission to use that command.");
		}
		return true;
	}
}
