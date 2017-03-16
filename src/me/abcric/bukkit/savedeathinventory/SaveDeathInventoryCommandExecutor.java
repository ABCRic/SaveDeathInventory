package me.abcric.bukkit.savedeathinventory;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SaveDeathInventoryCommandExecutor implements CommandExecutor {
	SaveDeathInventory plugin;
	
	public SaveDeathInventoryCommandExecutor(SaveDeathInventory plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("restore"))
			doRestore(sender, args);
		return false;
	}
	
	public void doRestore(CommandSender sender, String[] args) {
		
	}
}
