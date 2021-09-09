package dalabi.cannon.magicsand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dalabi.cannon.Config;
import dalabi.cannon.GeneralUtil;

public class MagicsandCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (!sender.hasPermission(Config.getConfig().magicsand_use_permission)) {
				GeneralUtil.sendMessage(player, Config.getConfig().magicsand_no_permission_message);
				return false;
			}
			if (args.length == 0) {
				GeneralUtil.sendMessage(player, Config.getConfig().magicsand_invalid_args_message);
				return false;
			}
			if (args.length == 1) {
				switch (args[0].toLowerCase()) {
				case "help":
					for (String message : Config.getConfig().magicsand_help_messages) {
						GeneralUtil.sendMessage(player, message);
					}
					break;
				case "sand":
					ItemStack sand = GeneralUtil.MakeItemStack(Config.getConfig().magicsand_sand_item_material, 1,
							Config.getConfig().magicsand_sand_item_data, Config.getConfig().magicsand_sand_item_name,
							Config.getConfig().magicsand_sand_item_lore);
					player.getInventory().addItem(sand);
					break;
				case "redsand":
					ItemStack redsand = GeneralUtil.MakeItemStack(Config.getConfig().magicsand_red_sand_item_material,
							1, Config.getConfig().magicsand_red_sand_item_data,
							Config.getConfig().magicsand_red_sand_item_name,
							Config.getConfig().magicsand_red_sand_item_lore);
					player.getInventory().addItem(redsand);
					break;
				case "gravel":
					ItemStack gravel = GeneralUtil.MakeItemStack(Config.getConfig().magicsand_gravel_item_material, 1,
							Config.getConfig().magicsand_gravel_item_data,
							Config.getConfig().magicsand_gravel_item_name,
							Config.getConfig().magicsand_gravel_item_lore);
					player.getInventory().addItem(gravel);
					break;
				case "refill":
					if (!MagicsandUtil.hasRefillCooldown(player)) {
						MagicsandUtil.refillMagicsand(player);
					}
					break;
				case "clear":
					if (!MagicsandUtil.hasClearCooldown(player)) {
						MagicsandUtil.clearMagicsand(player);
					}

					break;
				default:
					GeneralUtil.sendMessage(player, Config.getConfig().magicsand_invalid_args_message);
				}
				return false;
			} else {
				GeneralUtil.sendMessage(player, Config.getConfig().magicsand_invalid_args_message);
			}
		}
		return false;
	}

}
