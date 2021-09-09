package dalabi.cannon.buildhelper;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dalabi.cannon.Config;
import dalabi.cannon.GeneralUtil;
import dalabi.cannon.Storage;

public class BuildHelperCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (!player.hasPermission(Config.getConfig().build_helper_command_permission)) {
				GeneralUtil.sendMessage(player, Config.getConfig().build_helper_no_permission_message);
				return false;
			}
			if (Storage.getStorage().isUUIDinBuildhelperSet(player.getUniqueId())) {
				Storage.getStorage().removeFromBuildhelperSet(player.getUniqueId());
				GeneralUtil.sendMessage(player, Config.getConfig().build_helper_disabled_message);
				return false;
			}
			Storage.getStorage().addToBuildhelperSet(player.getUniqueId());
			GeneralUtil.sendMessage(player, Config.getConfig().build_helper_enabled_message);
		}
		return false;
	}

}
