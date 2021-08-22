package dalabi.cannon.magicsand;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.block.Block;

public class MagicsandClearable {
	
	private static final Map<Block, Integer> clearable = new ConcurrentHashMap<>();

	public static Map<Block, Integer> getClearableMap() {
		return clearable;
	}

	public static int getCounter(Block block) {
		return clearable.get(block);
	}

	public static void addToClearable(Block block) {
		clearable.put(block, 1);
	}

	public static void removeFromClearable(Block block) {
		clearable.remove(block);
	}

	public static void setCounter(Block block, int count) {
		clearable.put(block, count);
	}
}
