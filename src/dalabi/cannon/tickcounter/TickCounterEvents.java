package dalabi.cannon.tickcounter;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import dalabi.cannon.Config;
import dalabi.cannon.GeneralUtil;
import dalabi.cannon.Storage;

public class TickCounterEvents implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (event.getItem() == null) {
			return;
		}
		if (event.getItem().getType() == Material.AIR) {
			return;
		}
		if (event.getItem().getType() != Material.BLAZE_ROD) {
			return;
		}
		UUID uuid = event.getPlayer().getUniqueId();
		Action action = event.getAction();
		if (action == Action.LEFT_CLICK_AIR || action == Action.RIGHT_CLICK_AIR) {
			event.setCancelled(true);
			if (!Storage.getStorage().hasTicks(uuid)) {
				return;
			}
			Storage.getStorage().resetTicks(uuid);
			GeneralUtil.sendMessage(event.getPlayer(), Config.getConfig().tick_counter_ticks_reset_message);
			return;
		}
		Block block = event.getClickedBlock();
		Material material = block.getType();
		if (action == Action.RIGHT_CLICK_BLOCK
				&& (material == Material.DIODE_BLOCK_ON || material == Material.DIODE_BLOCK_OFF)) {
			switch ((int) block.getData()) {
			case 0:
			case 1:
			case 2:
			case 3:
				addTicks(uuid, 1);
				break;
			case 4:
			case 5:
			case 6:
			case 7:
				addTicks(uuid, 2);
				break;
			case 8:
			case 9:
			case 10:
			case 11:
				addTicks(uuid, 3);
				break;
			case 12:
			case 13:
			case 14:
			case 15:
				addTicks(uuid, 4);
				break;
			default:
			}
			event.setCancelled(true);
			GeneralUtil.sendMessage(event.getPlayer(),
					Config.getConfig().tick_counter_ticks_added_message
							.replace("%gt%", String.valueOf(Storage.getStorage().getTicks(uuid) * 2))
							.replace("%rt%", String.valueOf(Storage.getStorage().getTicks(uuid))));
		}
	}

	public void addTicks(UUID uuid, int count) {
		if (Storage.getStorage().hasTicks(uuid)) {
			int currentTicks = Storage.getStorage().getTicks(uuid);
			currentTicks += count;
			Storage.getStorage().setTicks(uuid, currentTicks);
			return;
		}
		Storage.getStorage().setTicks(uuid, count);
	}
}
