package dalabi.cannon;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class CooldownManager {
	
	private static Map<Player, Map<String, Long>> cooldownMap = new HashMap<>();

	public static Map<Player, Map<String, Long>> getCooldownMap() {
		return cooldownMap;
	}

	public static boolean hasActiveCooldown(Player player) {
		return cooldownMap.containsKey(player);
	}

	public static Map<String, Long> getPlayerCooldowns(Player player) {
		return cooldownMap.get(player);
	}

	public static void addCooldown(Player player, String type, long time) {
		cooldownMap.get(player).put(type, time);
	}

	public static void putCooldown(Player player, Map<String, Long> cooldown) {
		cooldownMap.put(player, cooldown);
	}

	public static void removeCooldown(Player player, String type) {
		cooldownMap.remove(player).remove(type);
	}
}
