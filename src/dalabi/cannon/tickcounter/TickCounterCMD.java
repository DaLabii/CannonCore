package dalabi.cannon.tickcounter;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dalabi.cannon.Config;
import dalabi.cannon.GeneralUtil;

public class TickCounterCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			ItemStack tickCounter = new ItemStack(Material.BLAZE_ROD, 1);
			player.getInventory().addItem(tickCounter);
			GeneralUtil.sendMessage(player, Config.getConfig().tick_counter_given_message);
		}
		return false;
	}

}
