package dalabi.cannon.fire;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.material.Button;

import dalabi.cannon.Config;
import dalabi.cannon.GeneralUtil;

public class FireCMD implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			final Player player = (Player) sender;
			if (!player.hasPermission(Config.button_command_permission)) {
				GeneralUtil.sendMSG(player, Config.button_no_permission_message);
				return false;
			}
			if (Fire.getButtonBlock(player) == null) {
				GeneralUtil.sendMSG(player, Config.button_not_found_message);
				return false;
			}
			Block block = Fire.getButtonBlock(player);
			if (block.getType() == Material.STONE_BUTTON) {
				Button button = (Button) block.getState().getData();
				FireUtil.updateButton(block, button, 21);
				GeneralUtil.sendMSG(player, Config.button_pressed_message.replace("%x%", String.valueOf(block.getX()))
						.replace("%y%", String.valueOf(block.getY())).replace("%z%", String.valueOf(block.getZ())));
				return false;
			}
			if (block.getType() == Material.WOOD_BUTTON) {
				final Button button = (Button) block.getState().getData();
				FireUtil.updateButton(block, button, 31);
				GeneralUtil.sendMSG(player, Config.button_pressed_message.replace("%x%", String.valueOf(block.getX()))
						.replace("%y%", String.valueOf(block.getY())).replace("%z%", String.valueOf(block.getZ())));
				return false;
			}
			GeneralUtil.sendMSG(player, Config.button_not_found_message);
		}
		return false;
	}

}
