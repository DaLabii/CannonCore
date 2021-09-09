package dalabi.cannon.lever;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.material.Lever;

import dalabi.cannon.Config;
import dalabi.cannon.GeneralUtil;
import dalabi.cannon.Storage;

public class LeverCMD implements CommandExecutor {

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (!player.hasPermission(Config.getConfig().lever_command_permission)) {
				GeneralUtil.sendMessage(player, Config.getConfig().lever_no_permission_message);
				return false;
			}
			if (Storage.getStorage().getLeverBlock(player.getUniqueId()) == null) {
				GeneralUtil.sendMessage(player, Config.getConfig().lever_not_found_message);
				return false;
			}
			Block block = Storage.getStorage().getLeverBlock(player.getUniqueId());
			if (block.getType() == Material.LEVER) {
				Lever lever = (Lever) block.getState().getData();
				int data = block.getData();
				data = data ^ 0x8;
				block.setData((byte) data);
				// updates nearby blocks, doesnt do it with just updating the lever itself
				Block supportBlock = block.getRelative(lever.getAttachedFace());
				BlockState initialSupportState = supportBlock.getState();
				BlockState supportState = supportBlock.getState();
				supportState.setType(Material.AIR);
				supportState.update(true, false);
				initialSupportState.update(true);
				GeneralUtil.sendMessage(player,
						Config.getConfig().lever_flicked_message.replace("%x%", String.valueOf(block.getX()))
								.replace("%y%", String.valueOf(block.getY()))
								.replace("%z%", String.valueOf(block.getZ())));
				return false;
			}
			GeneralUtil.sendMessage(player, Config.getConfig().lever_not_found_message);
		}
		return false;
	}

}
