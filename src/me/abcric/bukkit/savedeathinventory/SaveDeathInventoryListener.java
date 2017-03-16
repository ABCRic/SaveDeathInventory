package me.abcric.bukkit.savedeathinventory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class SaveDeathInventoryListener implements Listener {
	SaveDeathInventory plugin;

	public SaveDeathInventoryListener(SaveDeathInventory plugin) {
		this.plugin = plugin;
	}

	static String CurrentTimeString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date now = new Date();
		return sdf.format(now);
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		plugin.getLogger().info("Player death: " + event.getDeathMessage());

		Player p = event.getEntity();

		/* dump inventory to file */
		YamlConfiguration config = new YamlConfiguration();
		config.set("inventory", p.getInventory().getContents());
		config.set("armor", p.getInventory().getArmorContents());

		String filename = plugin.getDataFolder() + File.separator + CurrentTimeString() + "_" + p.getUniqueId()
				+ ".yml";

		try {
			if (!plugin.getDataFolder().exists())
				plugin.getDataFolder().mkdir();
			File f = new File(filename);
			if (!f.exists())
				f.createNewFile();
			config.save(f);
			plugin.getLogger().info("Inventory saved to " + f.getAbsolutePath());
		} catch (IOException e) {
			plugin.getLogger().severe("Could not save player inventory to file " + filename + ": " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		Player p = event.getPlayer();
		
		if(p.hasPermission("SaveDeathInventory.restore.free") || (p.hasPermission("SaveDeathInventory.restore") && plugin.restorePrice <= 0)) {
			p.sendMessage(ChatColor.GREEN + "Use /restore to get your inventory back.");
		} else if(p.hasPermission("SaveDeathInventory.restore") && plugin.restorePrice > 0) {
			p.sendMessage(ChatColor.GREEN + "Use /restore to get your inventory back for " + plugin.eco.format(plugin.restorePrice) + ".");
		}
	}
}
