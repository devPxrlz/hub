package dev.pxrlz.hub.adapter;

import java.util.List;

import org.bukkit.entity.Player;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import dev.pxrlz.hub.Hub;
import dev.pxrlz.hydra.Hydra;
import dev.pxrlz.hydra.profile.Profile;
import dev.pxrlz.hydra.queue.QueuePlayer;
import dev.pxrlz.hydra.util.constants.UnicodeConstants;
import dev.pxrlz.hydra.util.sidebar.SidebarAdapter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HubSidebarAdapter implements SidebarAdapter {

	private Hub instance;

	@Override
	public String getTitle(Player player) {
		return ("&b&lWashPvP &7" + UnicodeConstants.VERTICAL_BAR + " &fHub");
	}

	@Override
	public List<String> getLines(Player player) {
		List<String> lines = Lists.newArrayList();

		Profile profile = Hydra.getInstance().getProfileHandler().getById(player.getUniqueId());
		int totalPlayers = Hydra.getInstance().getServerHandler().getTotalPlayerCount();

		lines.add("&7&m" + Strings.repeat("-", 20));
		lines.add("&bRank");
		lines.add(profile.getActiveRank().getColoredName());
		lines.add(" ");
		lines.add("&bOnline");
		lines.add("&r" + totalPlayers + " / " + 120);

		if (QueuePlayer.isQueued(player.getUniqueId())) {
			lines.add(" ");
			lines.add("&bQueued for");
			lines.add("&e" + QueuePlayer.getQueue(player.getUniqueId()));
			lines.add("&rPosition: &r" + QueuePlayer.getPosition(player.getUniqueId()) + " / "
					+ instance.getIndraInstance().getQueueHandler()
							.getQueueByName(QueuePlayer.getQueue(player.getUniqueId())).getSize());
		}

		lines.add(" ");
		lines.add("&estore.washpvp.ga");
		lines.add("&7&m" + Strings.repeat("-", 20));

		return (lines);
	}
}
