package dalabi.cannon.fire;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.block.Block;

public class Fire {
	
	private static final Map<UUID, Block> buttons = new HashMap<>();

	public static Block getButtonBlock(UUID uuid) {
		return buttons.getOrDefault(uuid, null);
	}

	public static void setButtonBlock(UUID uuid, Block block) {
		buttons.put(uuid, block);
	}
}
