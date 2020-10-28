package dev.pxrlz.hub.adapter;

import java.util.List;

import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import dev.pxrlz.hub.Hub;
import dev.pxrlz.hub.server.ServerInfo;
import dev.pxrlz.hydra.Hydra;
import dev.pxrlz.hydra.profile.Profile;
import dev.pxrlz.hydra.util.tab.TabAdapter;
import dev.pxrlz.hydra.util.tab.TabEntry;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HubTabAdapter implements TabAdapter {

	private Hub instance;
	
	@Override
	public String getHeader(Player player) {
		return ("&bWelcome to &lWashPvP &bnetwork");
	}

	@Override
	public String getFooter(Player player) {
		return ("&bWelcome to &lWashPvP &bnetwork");
	}

	@Override
	public List<TabEntry> getLines(Player player) {
		List<TabEntry> entries = Lists.newArrayList();
		
		Profile profile = Hydra.getInstance().getProfileHandler().getById(player.getUniqueId());
		int totalPlayers = Hydra.getInstance().getServerHandler().getTotalPlayerCount();
		
		entries.add(new TabEntry(1, 0, "&b&lWashPvP"));
		entries.add(new TabEntry(1, 1, "&bOnline: &r" + totalPlayers));
		
		entries.add(new TabEntry(0, 3, "&b&lStore"));
		entries.add(new TabEntry(0, 4, "&fstore.washpvp.ga"));
		
		entries.add(new TabEntry(1, 3, "&b&lYour Rank"));
		entries.add(new TabEntry(1, 4, "&fRank: &r" + profile.getActiveRank().getColoredName()));
		
		entries.add(new TabEntry(2, 3, "&b&lTeamSpeak"));
		entries.add(new TabEntry(2, 4, "&fts.washpvp.ga"));
		
		entries.add(new TabEntry(1, 6, "&b&lServer Info"));
		
		ServerInfo kits = instance.getServerInfoHandler().getServerInfoByName("Kits");
		entries.add(new TabEntry(0, 8, "&b" + kits.getName() + " &r(" + kits.getPlayerCount() + ")"));
		entries.add(new TabEntry(0, 9, "&fKit: P1, S1"));
		entries.add(new TabEntry(0, 10, "&fTeams: 5 Man"));
		
		ServerInfo hcf = instance.getServerInfoHandler().getServerInfoByName("HCF");
		entries.add(new TabEntry(1, 8, "&b" + hcf.getName() + " &r(" + hcf.getPlayerCount() + ")"));
		entries.add(new TabEntry(1, 9, "&fKit: P1, S1"));
		entries.add(new TabEntry(1, 10, "&fTeams: 10 Man"));
		
		ServerInfo practice = instance.getServerInfoHandler().getServerInfoByName("Practice");
		entries.add(new TabEntry(2, 8, "&b" + practice.getName() + " &r(" + practice.getPlayerCount() + ")"));
		entries.add(new TabEntry(2, 9, "&fFree to Play"));
		entries.add(new TabEntry(2, 10, "&fLunar Protected"));
		
		return (entries);
	}
}
