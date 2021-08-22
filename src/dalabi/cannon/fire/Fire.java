package dalabi.cannon.fire;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Fire {
	
	private static final Map<Player, Block> buttons = new HashMap<>();

	public static Block getButtonBlock(Player player) {
		return buttons.getOrDefault(player, null);
	}

	public static void setButtonBlock(Player player, Block block) {
		buttons.put(player, block);
	}
}
