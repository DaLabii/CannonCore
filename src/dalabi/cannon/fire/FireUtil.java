package dalabi.cannon.fire;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.material.Button;

import dalabi.cannon.Main;

public class FireUtil {

	@SuppressWarnings("deprecation")
	public static void updateButton(Block block, Button button, int ticks) {
		int data = block.getData();
		data |= 0x8;
		block.setData((byte) data);
		updateNearbyBlocks(block, button);
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			@Override
			public void run() {
				int data = block.getData();
				data &= 0xFFFFFFF7;
				block.setData((byte) data);
				FireUtil.updateNearbyBlocks(block, button);
			}
		}, ticks);
	}

	public static void updateNearbyBlocks(Block block, Button button) {
		Block supportBlock = block.getRelative(button.getAttachedFace());
		BlockState initialSupportState = supportBlock.getState();
		BlockState supportState = supportBlock.getState();
		supportState.setType(Material.AIR);
		supportState.update(true, false);
		initialSupportState.update(true);
	}
}
