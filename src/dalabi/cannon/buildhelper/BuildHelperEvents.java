package dalabi.cannon.buildhelper;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import dalabi.cannon.Storage;

public class BuildHelperEvents implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if (!Storage.getStorage().isUUIDinBuildhelperSet(player.getUniqueId())) {
			return;
		}
		Block block = event.getBlock();
		int materialId = block.getTypeId();
		if (materialId == 89) {
			block.getRelative(0, 1, 0).setType(Material.REDSTONE_WIRE);
			return;
		}
		if (materialId == 44) {
			byte blockData = block.getData();
			if (blockData <= 7) {
				blockData = (byte) (blockData + 8);
			}
			block.setData(blockData);
			block.getRelative(0, 1, 0).setType(Material.REDSTONE_WIRE);
		}
	}
}
