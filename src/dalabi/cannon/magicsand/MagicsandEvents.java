package dalabi.cannon.magicsand;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;

import dalabi.cannon.Config;
import dalabi.cannon.GeneralUtil;
import dalabi.cannon.Main;
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
		if (material != Config.magicsand_sand_item_material && material != Config.magicsand_red_sand_item_material
				&& material != Config.magicsand_gravel_item_material) {
			return;
		}
		String displayName = player.getItemInHand().getItemMeta().getDisplayName();
		if (!displayName.contentEquals(ChatColor.translateAlternateColorCodes('&', Config.magicsand_sand_item_name))
				&& !displayName
						.contentEquals(ChatColor.translateAlternateColorCodes('&', Config.magicsand_red_sand_item_name))
				&& !displayName.contentEquals(
						ChatColor.translateAlternateColorCodes('&', Config.magicsand_gravel_item_name))) {
			return;
		}
		if (Magicsand.getMagicsandByPlayer(event.getPlayer().getUniqueId()) == null) {
			Set<Magicsand> msSet = new HashSet<>();
			Magicsand magicsand = new Magicsand(event.getBlock(),
					MagicsandUtil.translateMagicsandItemDataToMaterialID(event.getBlock().getData()),
					MagicsandUtil.translateMagicsandItemDataToBlockData(event.getBlock().getData()), 1);
			msSet.add(magicsand);
			Magicsand.putFirstMagicsand(event.getPlayer().getUniqueId(), msSet);
			event.getBlock().setMetadata("magicsand", new FixedMetadataValue(Main.getInstance(), "cannoncore"));
			return;
		}
		if (Magicsand.getMagicsandByPlayer(event.getPlayer().getUniqueId()).size() >= Config.magicsand_limit) {
			GeneralUtil.sendMSG(player, Config.magicsand_limit_reached_message);
			event.setCancelled(true);
			return;
		}
		Magicsand magicsand = new Magicsand(event.getBlock(),
				MagicsandUtil.translateMagicsandItemDataToMaterialID(event.getBlock().getData()),
				MagicsandUtil.translateMagicsandItemDataToBlockData(event.getBlock().getData()), 1);
		Magicsand.addMagicsand(event.getPlayer().getUniqueId(), magicsand);
		event.getBlock().setMetadata("magicsand", new FixedMetadataValue(Main.getInstance(), "cannoncore"));

	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onBreak(BlockBreakEvent event) {
		if (!event.getBlock().hasMetadata("magicsand")) {
			return;
		}
		Material material = event.getBlock().getType();
		if (material != Config.magicsand_sand_item_material && material != Config.magicsand_red_sand_item_material
				&& material != Config.magicsand_gravel_item_material) {
			return;
		}
		event.getBlock().removeMetadata("magicsand", Main.getInstance());
		MagicsandClearable.addToClearable(event.getBlock());
	}

}
