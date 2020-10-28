package dev.pxrlz.hub.server;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;

import dev.pxrlz.hub.Hub;
import dev.pxrlz.hub.server.menu.ServerSelectorMenu;
import dev.pxrlz.hydra.util.StringUtil;
import dev.pxrlz.hydra.util.maker.ItemMaker;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ServerInfoListener implements Listener {

	private Hub instance;
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onJoin(PlayerJoinEvent event) {
		event.setJoinMessage(null);
		Player player = event.getPlayer();
		
		player.teleport(player.getLocation().getWorld().getSpawnLocation());
		
		player.setGameMode(GameMode.SURVIVAL);
		player.setHealth(20);
		player.setFoodLevel(20);
		player.setExp(0);
		player.setLevel(0);
		
		player.getInventory().clear();
		
		ItemStack serverSelector = new ItemMaker(Material.PUMPKIN).name("&bServers").build();
		ItemStack enderPearl = new ItemMaker(Material.ENDER_PEARL).name("&bEnder Pearl").build();
		
		player.getInventory().setItem(8, enderPearl);
		player.getInventory().setItem(0, serverSelector);
		
		player.updateInventory();
		
		player.sendMessage(StringUtil.formatColor("&bYou have connected to &lHub&b."));
		player.sendMessage(StringUtil.formatColor("&7You can navigate servers via the pumpkin"));
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		
		if(event.getAction().name().contains("RIGHT")) {
			ItemStack hand = player.getItemInHand();
			if(hand != null) {
				
				if(hand.getType().equals(Material.ENDER_PEARL)) {
					player.setVelocity(event.getPlayer().getLocation().getDirection().multiply(2.5F));

			        event.setCancelled(true);
			        event.setUseItemInHand(Event.Result.DENY);

			        player.updateInventory();
				} else if(hand.getType().equals(Material.PUMPKIN)) {
					new ServerSelectorMenu(instance).openMenu(player);
				}
			}
		}
	}
	
	@EventHandler
	public void onChangeBlock(EntityChangeBlockEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onIgnite(BlockIgniteEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onPortal(PlayerPortalEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockBreak(BlockPlaceEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onSpawnEntity(CreatureSpawnEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		event.getDrops().clear();
		event.setDeathMessage(null);
	}

	@EventHandler
	public void onExplode(EntityExplodeEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onWeaterChange(WeatherChangeEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onEntityTarget(EntityTargetLivingEntityEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onClickInventory(InventoryClickEvent event) {
		if (event.getSlotType() == SlotType.QUICKBAR || event.getSlotType() == SlotType.ARMOR) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onFoodChange(FoodLevelChangeEvent event) {
		Player player = (Player) event.getEntity();
		
		player.setFoodLevel(20);
		event.setFoodLevel(20);
		
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		Entity entity = event.getEntity();
		
		if (event.getCause() != EntityDamageEvent.DamageCause.CONTACT) {
			event.setCancelled(true);
		} else if (event.getCause() == EntityDamageEvent.DamageCause.VOID) {
			event.setCancelled(true);
			
			entity.teleport(entity.getLocation().getWorld().getSpawnLocation());
		}
	}
}
