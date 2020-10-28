package dev.pxrlz.hub.server.menu;

import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import com.google.common.collect.Maps;

import dev.pxrlz.hub.Hub;
import dev.pxrlz.hub.server.ServerInfo;
import dev.pxrlz.hydra.queue.QueuePlayer;
import dev.pxrlz.hydra.util.StringUtil;
import dev.pxrlz.hydra.util.menus.Menu;
import dev.pxrlz.hydra.util.menus.button.Button;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ServerSelectorMenu extends Menu {

	private Hub instance;
	
	@Override
	public String getTitle(Player player) {
		return ("&aSelector Module");
	}

	@Override
	public Map<Integer, Button> getButtons(Player player) {
		Map<Integer, Button> button = Maps.newHashMap();
		
		button.put(getSlot(0, 0), Button.placeholder(Material.STAINED_GLASS_PANE, (byte) 3, "&0&l"));
		button.put(getSlot(1, 0), Button.placeholder(Material.STAINED_GLASS_PANE, (byte) 3, "&0&l"));
		button.put(getSlot(0, 1), Button.placeholder(Material.STAINED_GLASS_PANE, (byte) 3, "&0&l"));
		
		button.put(getSlot(8, 5), Button.placeholder(Material.STAINED_GLASS_PANE, (byte) 3, "&0&l"));
		button.put(getSlot(8, 4), Button.placeholder(Material.STAINED_GLASS_PANE, (byte) 3, "&0&l"));
		button.put(getSlot(7, 5), Button.placeholder(Material.STAINED_GLASS_PANE, (byte) 3, "&0&l"));
		
		button.put(getSlot(8, 0), Button.placeholder(Material.STAINED_GLASS_PANE, (byte) 0, "&0&l"));
		button.put(getSlot(8, 1), Button.placeholder(Material.STAINED_GLASS_PANE, (byte) 0, "&0&l"));
		button.put(getSlot(7, 0), Button.placeholder(Material.STAINED_GLASS_PANE, (byte) 0, "&0&l"));
		
		button.put(getSlot(0, 5), Button.placeholder(Material.STAINED_GLASS_PANE, (byte) 0, "&0&l"));
		button.put(getSlot(0, 4), Button.placeholder(Material.STAINED_GLASS_PANE, (byte) 0, "&0&l"));
		button.put(getSlot(1, 5), Button.placeholder(Material.STAINED_GLASS_PANE, (byte) 0, "&0&l"));
		
		button.put(getSlot(4, 2), new ServerInfoButton(instance.getServerInfoHandler().getServerInfoByName("HCF")));
		button.put(getSlot(2, 3), new ServerInfoButton(instance.getServerInfoHandler().getServerInfoByName("Kits")));
		button.put(getSlot(6, 3), new ServerInfoButton(instance.getServerInfoHandler().getServerInfoByName("Practice")));
		
		return (button);
	}
	
	@AllArgsConstructor
	private class ServerInfoButton extends Button {
		
		private ServerInfo serverInfo;

		@Override
		public ItemStack getButtonItem(Player player) {
			return serverInfo.getItemSelector();
		}
		
		@Override
		public void clicked(Player player, ClickType clickType) {
			if(QueuePlayer.isQueued(player.getUniqueId())) {
				player.closeInventory();
				
				player.sendMessage(StringUtil.formatColor("&cYou are already in a queue!"));
				return;
			} 
			
			QueuePlayer.join(player, serverInfo.getQueue());
		}
	}
}
