package dalabi.cannon.lever;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class LeverEvents implements Listener{

	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}
		final Material clicked = event.getClickedBlock().getType();
		if (clicked != Material.LEVER) {
			return;
		}
		dalabi.cannon.lever.Lever.setLeverBlock(event.getPlayer().getUniqueId(), event.getClickedBlock());
	}
}
