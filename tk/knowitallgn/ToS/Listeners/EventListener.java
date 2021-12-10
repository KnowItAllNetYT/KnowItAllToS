package tk.knowitallgn.ToS.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;

import tk.knowitallgn.ToS.ToS;
import tk.knowitallgn.ToS.DataTypes.PlayerFile;

public class EventListener implements Listener{

	private ToS plugin;
	
	public EventListener(ToS plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event){
		if(!plugin.playerFiles.containsKey(event.getPlayer().getName())){
			plugin.playerFiles.put(event.getPlayer().getName(), new PlayerFile());
		}
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		if(plugin.config.getBoolean("Options.Move.Deny")){
			if(!plugin.playerFiles.get(event.getPlayer().getName()).hasAgreed()){
				event.setCancelled(true);
				if(plugin.config.getBoolean("Options.Move.Message")){
					event.getPlayer().sendMessage(plugin.config.getString("Messages.DenyMessage").replaceAll("(&([a-f0-9l-or]))", "\u00A7$2"));
				}
			}
		}
	}
	
	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent event){
		if(plugin.config.getBoolean("Options.Talk.Deny")){
			if(!plugin.playerFiles.get(event.getPlayer().getName()).hasAgreed()){
				event.setCancelled(true);
				if(plugin.config.getBoolean("Options.Talk.Message")){
					event.getPlayer().sendMessage(plugin.config.getString("Messages.DenyMessage").replaceAll("(&([a-f0-9l-or]))", "\u00A7$2"));
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		if(plugin.config.getBoolean("Options.BlockInteract.Deny")){
			if(!plugin.playerFiles.get(event.getPlayer().getName()).hasAgreed()){
				event.setCancelled(true);
				if(plugin.config.getBoolean("Options.BlockInteract.Message")){
					event.getPlayer().sendMessage(plugin.config.getString("Messages.DenyMessage").replaceAll("(&([a-f0-9l-or]))", "\u00A7$2"));
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event){
		if(plugin.config.getBoolean("Options.EntityInteract.Deny")){
			if(!plugin.playerFiles.get(event.getPlayer().getName()).hasAgreed()){
				event.setCancelled(true);
				if(plugin.config.getBoolean("Options.EntityInteract.Message")){
					event.getPlayer().sendMessage(plugin.config.getString("Messages.DenyMessage").replaceAll("(&([a-f0-9l-or]))", "\u00A7$2"));
				}
			}
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		if(plugin.config.getBoolean("Options.BlockPlace.Deny")){
			if(!plugin.playerFiles.get(event.getPlayer().getName()).hasAgreed()){
				event.setCancelled(true);
				if(plugin.config.getBoolean("Options.BlockPlace.Message")){
					event.getPlayer().sendMessage(plugin.config.getString("Messages.DenyMessage").replaceAll("(&([a-f0-9l-or]))", "\u00A7$2"));
				}
			}
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		if(plugin.config.getBoolean("Options.BlockBreak.Deny")){
			if(!plugin.playerFiles.get(event.getPlayer().getName()).hasAgreed()){
				event.setCancelled(true);
				if(plugin.config.getBoolean("Options.BlockBreak.Message")){
					event.getPlayer().sendMessage(plugin.config.getString("Messages.DenyMessage").replaceAll("(&([a-f0-9l-or]))", "\u00A7$2"));
				}
			}
		}
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		if(event.getWhoClicked() instanceof Player){
			if(plugin.config.getBoolean("Options.InventoryClick.Deny")){
				if(!plugin.playerFiles.get(event.getWhoClicked().getName()).hasAgreed()){
					event.setCancelled(true);
					if(plugin.config.getBoolean("Options.InventoryClick.Message")){
						((Player) event.getWhoClicked()).sendMessage(plugin.config.getString("Messages.DenyMessage").replaceAll("(&([a-f0-9l-or]))", "\u00A7$2"));
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent event){
		if(plugin.config.getBoolean("Options.ItemPickup.Deny")){
			if(!plugin.playerFiles.get(event.getPlayer().getName()).hasAgreed()){
				event.setCancelled(true);
				if(plugin.config.getBoolean("Options.ItemPickup.Message")){
					event.getPlayer().sendMessage(plugin.config.getString("Messages.DenyMessage").replaceAll("(&([a-f0-9l-or]))", "\u00A7$2"));
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event){
		if(plugin.config.getBoolean("Options.ItemDrop.Deny")){
			if(!plugin.playerFiles.get(event.getPlayer().getName()).hasAgreed()){
				event.setCancelled(true);
				if(plugin.config.getBoolean("Options.ItemDrop.Message")){
					event.getPlayer().sendMessage(plugin.config.getString("Messages.DenyMessage").replaceAll("(&([a-f0-9l-or]))", "\u00A7$2"));
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerItemConsume(PlayerItemConsumeEvent event){
		if(plugin.config.getBoolean("Options.ItemConsume.Deny")){
			if(!plugin.playerFiles.get(event.getPlayer().getName()).hasAgreed()){
				event.setCancelled(true);
				if(plugin.config.getBoolean("Options.ItemConsume.Message")){
					event.getPlayer().sendMessage(plugin.config.getString("Messages.DenyMessage").replaceAll("(&([a-f0-9l-or]))", "\u00A7$2"));
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerItemHeld(PlayerItemHeldEvent event){
		if(plugin.config.getBoolean("Options.ItemHeld.Deny")){
			if(!plugin.playerFiles.get(event.getPlayer().getName()).hasAgreed()){
				((Cancellable) event).setCancelled(true);
				if(plugin.config.getBoolean("Options.ItemHeld.Message")){
					event.getPlayer().sendMessage(plugin.config.getString("Messages.DenyMessage").replaceAll("(&([a-f0-9l-or]))", "\u00A7$2"));
				}
			}
		}
	}
	
	@EventHandler
	public void onProjectileLaunch(ProjectileLaunchEvent event){
		if(event.getEntity().getShooter() instanceof Player){
			if(plugin.config.getBoolean("Options.ProjectileLaunch.Deny")){
				if(!plugin.playerFiles.get(((Player) event.getEntity().getShooter()).getName()).hasAgreed()){
					event.setCancelled(true);
					if(plugin.config.getBoolean("Options.ProjectileLaunch.Message")){
						((Player) event.getEntity().getShooter()).sendMessage(plugin.config.getString("Messages.DenyMessage").replaceAll("(&([a-f0-9l-or]))", "\u00A7$2"));
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
		if(event.getDamager() instanceof Player){
			if(plugin.config.getBoolean("Options.Attack.Deny")){
				if(!plugin.playerFiles.get(((Player) event.getDamager()).getName()).hasAgreed()){
					event.setCancelled(true);
					if(plugin.config.getBoolean("Options.Attack.Message")){
						((Player) event.getDamager()).sendMessage(plugin.config.getString("Messages.DenyMessage").replaceAll("(&([a-f0-9l-or]))", "\u00A7$2"));
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event){
		if(event.getEntity() instanceof Player){
			if(plugin.config.getBoolean("Options.Damage.Deny")){
				if(!plugin.playerFiles.get(((Player) event.getEntity()).getName()).hasAgreed()){
					event.setCancelled(true);
					if(plugin.config.getBoolean("Options.Damage.Message")){
						((Player) event.getEntity()).sendMessage(plugin.config.getString("Messages.DenyMessage").replaceAll("(&([a-f0-9l-or]))", "\u00A7$2"));
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event){
		if(plugin.config.getBoolean("Options.BucketEmpty.Deny")){
			if(!plugin.playerFiles.get(event.getPlayer().getName()).hasAgreed()){
				event.setCancelled(true);
				if(plugin.config.getBoolean("Options.BucketEmpty.Message")){
					event.getPlayer().sendMessage(plugin.config.getString("Messages.DenyMessage").replaceAll("(&([a-f0-9l-or]))", "\u00A7$2"));
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerBucketFill(PlayerBucketFillEvent event){
		if(plugin.config.getBoolean("Options.BucketFill.Deny")){
			if(!plugin.playerFiles.get(event.getPlayer().getName()).hasAgreed()){
				event.setCancelled(true);
				if(plugin.config.getBoolean("Options.BucketFill.Message")){
					event.getPlayer().sendMessage(plugin.config.getString("Messages.DenyMessage").replaceAll("(&([a-f0-9l-or]))", "\u00A7$2"));
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerToggleSneak(PlayerToggleSneakEvent event){
		if(plugin.config.getBoolean("Options.ToggleSneak.Deny")){
			if(!plugin.playerFiles.get(event.getPlayer().getName()).hasAgreed()){
				event.setCancelled(true);
				if(plugin.config.getBoolean("Options.ToggleSneak.Message")){
					event.getPlayer().sendMessage(plugin.config.getString("Messages.DenyMessage").replaceAll("(&([a-f0-9l-or]))", "\u00A7$2"));
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerToggleSprint(PlayerToggleSprintEvent event){
		if(plugin.config.getBoolean("Options.ToggleSprint.Deny")){
			if(!plugin.playerFiles.get(event.getPlayer().getName()).hasAgreed()){
				event.setCancelled(true);
				if(plugin.config.getBoolean("Options.ToggleSprint.Message")){
					event.getPlayer().sendMessage(plugin.config.getString("Messages.DenyMessage").replaceAll("(&([a-f0-9l-or]))", "\u00A7$2"));
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerExpChange(PlayerExpChangeEvent event){
		if(plugin.config.getBoolean("Options.ExpChange.Deny")){
			if(!plugin.playerFiles.get(event.getPlayer().getName()).hasAgreed()){
				event.setAmount(0);
				if(plugin.config.getBoolean("Options.ExpChange.Message")){
					event.getPlayer().sendMessage(plugin.config.getString("Messages.DenyMessage").replaceAll("(&([a-f0-9l-or]))", "\u00A7$2"));
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerPortal(PlayerPortalEvent event){
		if(plugin.config.getBoolean("Options.PortalUsage.Deny")){
			if(!plugin.playerFiles.get(event.getPlayer().getName()).hasAgreed()){
				event.setCancelled(true);
				if(plugin.config.getBoolean("Options.PortalUsage.Message")){
					event.getPlayer().sendMessage(plugin.config.getString("Messages.DenyMessage").replaceAll("(&([a-f0-9l-or]))", "\u00A7$2"));
				}
			}
		}
	}
}
