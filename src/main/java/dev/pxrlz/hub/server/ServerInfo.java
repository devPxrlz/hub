package dev.pxrlz.hub.server;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import dev.pxrlz.hub.Hub;
import dev.pxrlz.hydra.Hydra;
import dev.pxrlz.hydra.queue.Queue;
import dev.pxrlz.hydra.server.Server;
import dev.pxrlz.hydra.util.StringUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ServerInfo {

	private final Hub instance;
	private final String name;

	private final Material material;
	private final int data;

	private final String title;
	private final List<String> lore;

	public Queue getQueue() {
		return (instance.getIndraInstance().getQueueHandler().getQueueByName(name));
	}

	public String getStatusColor() {
		Server server = Hydra.getInstance().getServerHandler().getServerByName(name);
		return StringUtil.formatColor((server == null ? ("&c&l") : ("&a&l")));
	}

	public String getStatus() {
		Server server = Hydra.getInstance().getServerHandler().getServerByName(name);
		return (server == null ? ("&cOffline") : (server.isWhitelist() ? ("&eWhitelisted") : ("&aOnline")));
	}

	public int getPlayerCount() {
		Server server = Hydra.getInstance().getServerHandler().getServerByName(name);
		return (server == null ? (0) : (server.getPlayerCount()));
	}

	public int getMaxSlots() {
		Server server = Hydra.getInstance().getServerHandler().getServerByName(name);
		return (server == null ? (0) : (server.getMaxSlots()));
	}

	public ItemStack getItemSelector() {
		ItemStack itemStack = new ItemStack(material, 1, (short) data);
		ItemMeta itemMeta = itemStack.getItemMeta();

		itemMeta.setDisplayName(StringUtil.formatColor(title.replace("<server-count>", String.valueOf(getPlayerCount()))
				.replace("<server-status>", getStatus()).replace("<server-maxslots>", String.valueOf(getMaxSlots()))
				.replace("<server-status-color>", getStatusColor()))
				.replace("<server-queue-size>", String.valueOf(getQueue().getSize()))
				.replace("<server-queue-paused>", (getQueue().isPaused() ? ("&cPaused") : ("")))
				.replace("<server-queue-open>", (getQueue().isOpen() ? ("") : ("&cClosed"))));
		itemMeta.setLore(StringUtil.formatColor(lore.stream()
				.map(string -> string.replace("<server-count>", String.valueOf(getPlayerCount()))
						.replace("<server-status>", getStatus())
						.replace("<server-maxslots>", String.valueOf(getMaxSlots()))
						.replace("<server-status-color>", getStatusColor())
						.replace("<server-queue-size>", String.valueOf(getQueue().getSize()))
						.replace("<server-queue-paused>", (getQueue().isPaused() ? ("&cPaused") : ("")))
						.replace("<server-queue-open>", (getQueue().isOpen() ? ("") : ("&cClosed"))))
				.collect(Collectors.toList())));

		itemStack.setItemMeta(itemMeta);

		return (itemStack);
	}
}
