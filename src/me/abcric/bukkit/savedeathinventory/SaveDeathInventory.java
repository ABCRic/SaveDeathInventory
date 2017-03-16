package me.abcric.bukkit.savedeathinventory;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;

public class SaveDeathInventory extends JavaPlugin {
	public Economy eco; // Economy instance for money operations
	
	@Override
	public void onEnable() {
		getLogger().info("Starting up " + getDescription().getName() + " " + getDescription().getVersion() + " by "
				+ String.join(", ", getDescription().getAuthors()) + "...");
		getServer().getPluginManager().registerEvents(new SaveDeathInventoryListener(this), this);
		getCommand("restore").setExecutor(new SaveDeathInventoryCommandExecutor(this));

		// load economy from Vault
		getLogger().info("Setting up economy...");
		if (!setupEconomy()) {
			getLogger().severe("Vault not found, launch failed.");
			getLogger().severe("This plugin requires Vault. Please obtain Vault before using this plugin.");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		
		getLogger().info("Enabled.");
	}
	
	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer()
				.getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		eco = rsp.getProvider();
		return eco != null;
	}
}
