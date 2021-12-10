package tk.knowitallgn.ToS.Tasks;

import org.bukkit.scheduler.BukkitRunnable;

import tk.knowitallgn.ToS.ToS;

public class SaveTask extends BukkitRunnable{

	private ToS plugin;
	
	public SaveTask(ToS plugin){
		this.plugin = plugin;
	}
	
	public void run(){
		plugin.saveData();
	}
}
