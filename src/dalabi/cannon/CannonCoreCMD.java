package dalabi.cannon;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CannonCoreCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			final Player player = (Player) sender;
			if (!player.hasPermission(Config.cannoncore_reload_permission)) {
				GeneralUtil.sendMSG(player, Config.cannoncore_no_permission_message);
				return false;
			}
			if (args.length == 1) {
				switch (args[0].toLowerCase()) {
				case "reload":
					Config.reload();
					GeneralUtil.sendMSG(player, Config.cannoncore_reload_message);
					break;
				default:
					GeneralUtil.sendMSG(player, Config.cannoncore_invalid_args_message);
				}
			}
		}
		return false;
	}
}
