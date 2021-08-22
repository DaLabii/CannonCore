package dalabi.cannon.magicsand;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;

public class MagicsandRemovable {
	
	private static Map<Player, List<Magicsand>> removable = new ConcurrentHashMap<>();

	public static Map<Player, List<Magicsand>> getRemovable() {
		return removable;
	}

	public static boolean containsPlayer(Player player) {
		return removable.containsKey(player);
	}

	public static void addToRemovable(Player player, Magicsand block) {
		removable.get(player).add(block);
	}

	public static void putInRemovable(Player player, List<Magicsand> blocks) {
		removable.put(player, blocks);
	}

	public static void clearRemovable() {
		removable.clear();
	}
}
