package dalabi.cannon.fire;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class FireEvents implements Listener {
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}
		final Material clicked = event.getClickedBlock().getType();
		if (clicked != Material.STONE_BUTTON && clicked != Material.WOOD_BUTTON) {
			return;
		}
		Fire.setButtonBlock(event.getPlayer(), event.getClickedBlock());
	}
}
