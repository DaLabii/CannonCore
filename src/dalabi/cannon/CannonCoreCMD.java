package dalabi.cannon;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CannonCoreCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (!player.hasPermission(Config.getConfig().cannoncore_reload_permission)) {
				GeneralUtil.sendMessage(player, Config.getConfig().cannoncore_no_permission_message);
				return false;
			}
			if (args.length == 1) {
				switch (args[0].toLowerCase()) {
				case "reload":
					Config.getConfig().reload();
					GeneralUtil.sendMessage(player, Config.getConfig().cannoncore_reload_message);
					break;
				default:
					GeneralUtil.sendMessage(player, Config.getConfig().cannoncore_invalid_args_message);
				}
			}
		}
		return false;
	}
}
