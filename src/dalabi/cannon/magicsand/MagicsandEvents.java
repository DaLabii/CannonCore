package dalabi.cannon.magicsand;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import dalabi.cannon.Config;
import dalabi.cannon.GeneralUtil;
import dalabi.cannon.Storage;
import net.md_5.bungee.api.ChatColor;

public class MagicsandEvents implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if (player.getItemInHand().getItemMeta().getDisplayName() == null) {
			return;
		}
		Material material = event.getBlock().getType();
		if (material != Config.getConfig().magicsand_sand_item_material
				&& material != Config.getConfig().magicsand_red_sand_item_material
				&& material != Config.getConfig().magicsand_gravel_item_material) {
			return;
		}
		String displayName = player.getItemInHand().getItemMeta().getDisplayName();
		if (!displayName
				.contentEquals(ChatColor.translateAlternateColorCodes('&', Config.getConfig().magicsand_sand_item_name))
				&& !displayName.contentEquals(
						ChatColor.translateAlternateColorCodes('&', Config.getConfig().magicsand_red_sand_item_name))
				&& !displayName.contentEquals(
						ChatColor.translateAlternateColorCodes('&', Config.getConfig().magicsand_gravel_item_name))) {
			return;
		}
		if (Storage.getStorage().hasMagicsand(event.getPlayer().getUniqueId()) && Storage.getStorage()
				.getMagicsandAmount(event.getPlayer().getUniqueId()) >= Config.getConfig().magicsand_limit) {
			GeneralUtil.sendMessage(player, Config.getConfig().magicsand_limit_reached_message);
			event.setCancelled(true);
			return;
		}
		Magicsand magicsand = new Magicsand(event.getBlock(),
				MagicsandUtil.translateMagicsandItemDataToMaterialID(event.getBlock().getData()),
				MagicsandUtil.translateMagicsandItemDataToBlockData(event.getBlock().getData()), 1,
				player.getUniqueId());
		Storage.getStorage().addToMagicsand(magicsand);
		Storage.getStorage().addToMagicsandBlockSet(event.getBlock());
		if (Storage.getStorage().hasMagicsand(player.getUniqueId())) {
			int currentAmount = Storage.getStorage().getMagicsandAmount(player.getUniqueId());
			Storage.getStorage().setMagicsandAmount(player.getUniqueId(), currentAmount + 1);
			return;
		}
		Storage.getStorage().setMagicsandAmount(player.getUniqueId(), 1);
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onBreak(BlockBreakEvent event) {
		if (!Storage.getStorage().isBlockInMagicsandBlockSet(event.getBlock())) {
			return;
		}
		Material material = event.getBlock().getType();
		if (material != Config.getConfig().magicsand_sand_item_material
				&& material != Config.getConfig().magicsand_red_sand_item_material
				&& material != Config.getConfig().magicsand_gravel_item_material) {
			return;
		}
		Block block = event.getBlock();
		Storage.getStorage().addToClearable(block);
		for (Magicsand magicsand : Storage.getStorage().getMagicsandList()) {
			if (block.getLocation() == magicsand.getBlock().getLocation()) {
				Storage.getStorage().addToRemovable(magicsand);
				break;
			}
		}
	}

}
