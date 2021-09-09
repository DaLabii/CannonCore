package dalabi.cannon.magicsand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dalabi.cannon.Config;
import dalabi.cannon.GeneralUtil;

public class MagicsandRefillCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (!sender.hasPermission(Config.getConfig().magicsand_use_permission)) {
				GeneralUtil.sendMessage(player, Config.getConfig().magicsand_no_permission_message);
				return false;
			}
			if (!MagicsandUtil.hasRefillCooldown(player)) {
				MagicsandUtil.refillMagicsand(player);
			}
		}
		return false;
	}
}
