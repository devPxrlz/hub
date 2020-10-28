package dev.pxrlz.hub;

import org.bukkit.plugin.java.JavaPlugin;

import dev.pxrlz.hub.adapter.HubSidebarAdapter;
import dev.pxrlz.hub.adapter.HubTabAdapter;
import dev.pxrlz.hub.server.ServerInfoHandler;
import dev.pxrlz.hydra.Hydra;
import dev.pxrlz.hydra.util.indigo.bukkit.BukkitCommandHandler;
import dev.pxrlz.hydra.util.sidebar.Sidebar;
import dev.pxrlz.hydra.util.tab.Tab;
import lombok.Getter;

@Getter
public class Hub extends JavaPlugin {

	private BukkitCommandHandler commandHandler;
	private ServerInfoHandler serverInfoHandler;
		
	@Override
	public void onEnable() {
		loadHandlers();
		startup();
	}
	
	private void loadHandlers() {
		commandHandler = new BukkitCommandHandler(this);
		serverInfoHandler = new ServerInfoHandler(this);
	}
	
	private void startup() {
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		new Sidebar(this, new HubSidebarAdapter(this));
		new Tab(this, new HubTabAdapter(this));
		
		commandHandler.applyTo(getName());
	}
	
	public static Hub getInstance() { 
		return (JavaPlugin.getPlugin(Hub.class));
	}
	
	public Hydra getIndraInstance() {
		return (Hydra.getInstance());
	}
}
