package me.abcric.bukkit.savedeathinventory;

import org.bukkit.plugin.java.JavaPlugin;

public class SaveDeathInventory extends JavaPlugin {
	@Override
	public void onEnable() {
		getLogger().info("Starting up " + getDescription().getName() + " " + getDescription().getVersion() + " by "
				+ String.join(", ", getDescription().getAuthors()) + "...");
		getServer().getPluginManager().registerEvents(new SaveDeathInventoryListener(this), this);
		getLogger().info("Enabled.");
	}

	@Override
	public void onDisable() {
		
	} 
}
