package dalabi.cannon.lever;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.block.Block;

public class Lever {

	private static final Map<UUID, Block> levers = new HashMap<>();

	public static Block getLeverBlock(UUID uuid) {
		return levers.getOrDefault(uuid, null);
	}

	public static void setLeverBlock(UUID uuid, Block block) {
		levers.put(uuid, block);
	}
}
