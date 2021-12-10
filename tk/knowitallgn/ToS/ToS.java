package tk.knowitallgn.ToS;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import tk.knowitallgn.ToS.Commands.CommandIAgree;
import tk.knowitallgn.ToS.Commands.CommandRules;
import tk.knowitallgn.ToS.Commands.CommandToS;
import tk.knowitallgn.ToS.DataTypes.PlayerFile;
import tk.knowitallgn.ToS.Listeners.EventListener;
import tk.knowitallgn.ToS.Tasks.SaveTask;

public class ToS extends JavaPlugin{

	private Logger logger = Bukkit.getLogger();
	public Map<String, PlayerFile> playerFiles = new HashMap<String, PlayerFile>();
	public FileConfiguration config = null;
	public BukkitTask saveTask = null;
	public BukkitTask messageTask = null;
	
	public void onEnable(){
		getConfig().options().copyDefaults(true);
		saveConfig();
		config = getConfig();
		logger.log(Level.INFO, "[KnowItAllToS] Configuration file loaded!");
		getServer().getPluginManager().registerEvents(new EventListener(this), this);
		logger.log(Level.INFO, "[KnowItAllToS] Events registered!");
		getCommand("Rules").setExecutor(new CommandRules(this));
		getCommand("IAgree").setExecutor(new CommandIAgree(this));
		getCommand("ToS").setExecutor(new CommandToS(this));
		logger.log(Level.INFO, "[KnowItAllToS] Commands registered!");
		loadData();
		startTasks();
		logger.log(Level.INFO, "[KnowItAllToS] Version " + getDescription().getVersion() + " has been enabled.");
	}
	
	public void onDisable(){
		stopTasks();
		saveData();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadData(){
		Long time = System.currentTimeMillis();
		try{
			FileInputStream fis = new FileInputStream(getDataFolder() + "/PlayerFiles.ser");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        playerFiles = (HashMap) ois.readObject();
	        ois.close();
		}catch(FileNotFoundException e){
			logger.log(Level.WARNING, "[ToS] Data file PlayerFiles.ser is missing!");
		}catch(IOException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		logger.log(Level.INFO, "[KnowItAllToS] Data loaded! (" + String.valueOf(System.currentTimeMillis() - time) + "ms)");
	}
	
	@SuppressWarnings("rawtypes")
	public void saveData(){
		Long time = System.currentTimeMillis();
		try{
	        Map map = playerFiles;
	        FileOutputStream fos = new FileOutputStream(getDataFolder() + "/PlayerFiles.ser");
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(map);
	        oos.close();
		}catch(FileNotFoundException e){
			logger.log(Level.WARNING, "[KnowItAllToS] Data file PlayerFiles.ser is missing!");
		}catch(IOException e){
			e.printStackTrace();
		}
		logger.log(Level.INFO, "[KnowItAllToS] Data saved! (" + String.valueOf(System.currentTimeMillis() - time) + "ms)");
	}
	
	public void startTasks(){
		if(getConfig().getBoolean("Tasks.SaveTask.Enabled")){
			Integer interval = getConfig().getInt("Tasks.SaveTask.Interval") * 20;
			saveTask = new SaveTask(this).runTaskTimer(this, interval, interval);
			logger.log(Level.INFO, "[KnowItAllToS] Save task started!");
		}
	}
	
	public void stopTasks(){
		if(saveTask != null){
			saveTask.cancel();
			saveTask = null;
			logger.log(Level.INFO, "[KnowItAllToS] Save task stopped!");
		}
		if(messageTask != null){
			messageTask.cancel();
			messageTask = null;
			logger.log(Level.INFO, "[KnowItAllToS] Message task stopped!");
		}
		
	}
}
