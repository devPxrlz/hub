package dev.pxrlz.hub.server;

import java.util.Arrays;
import java.util.Map;

import org.bukkit.Material;

import com.google.common.collect.Maps;

import dev.pxrlz.hub.Hub;
import lombok.Getter;

public class ServerInfoHandler {

	@Getter
	private Map<String, ServerInfo> servers = Maps.newHashMap();

	public ServerInfoHandler(Hub instance) {
		servers.put("HCF",
				new ServerInfo(instance, "HCF", Material.DIAMOND_SWORD, 0, "<server-status-color>HCF",
						Arrays.asList("&fHCF has custom enchants, /kits", "&fand plenty of other features that",
								"&fmake HCF much more fast paced.", " ",
								"&bPlayers&7: &r<server-count> / <server-maxslots> &7(<server-queue-size> queued)", " ",
								"&fClick to join queue...", " ")));

		servers.put("Kits",
				new ServerInfo(instance, "Kits", Material.BOW, 0, "<server-status-color>Kits",
						Arrays.asList("&fKits allow you to practice your", "&fskills and aim for a spot on the",
								"&fleaderboards.", " ",
								"&bPlayers&7: &r<server-count> / <server-maxslots> &7(<server-queue-size> queued)", " ",
								"&fClick to join queue...", " ")));

		servers.put("Practice",
				new ServerInfo(instance, "Practice", Material.POTION, 67, "<server-status-color>Practice",
						Arrays.asList("&fDuel your oponents and work", "&fyour way up the leaderboards.", " ",
								"&bPlayers&7: &r<server-count> / <server-maxslots> &7(<server-queue-size> queued)", " ",
								"&fClick to join queue...", " ")));

		instance.getServer().getPluginManager().registerEvents(new ServerInfoListener(instance), instance);
	}

	public ServerInfo getServerInfoByName(String name) {
		for (ServerInfo serverInfo : servers.values()) {
			if (serverInfo.getName().equalsIgnoreCase(name)) {
				return (serverInfo);
			}
		}

		return (null);
	}
}