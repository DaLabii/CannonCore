package dalabi.cannon.magicsand;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MagicsandRemovable {
	
	private static Map<UUID, List<Magicsand>> removable = new ConcurrentHashMap<>();

	public static Map<UUID, List<Magicsand>> getRemovable() {
		return removable;
	}

	public static boolean containsPlayer(UUID uuid) {
		return removable.containsKey(uuid);
	}

	public static void addToRemovable(UUID uuid, Magicsand block) {
		removable.get(uuid).add(block);
	}

	public static void putInRemovable(UUID uuid, List<Magicsand> blocks) {
		removable.put(uuid, blocks);
	}

	public static void clearRemovable() {
		removable.clear();
	}
}
