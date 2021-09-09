package dalabi.cannon;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

	private static CooldownManager manager;

	public CooldownManager() {
		manager = this;
	}

	public static CooldownManager getCooldownManager() {
		return manager;
	}

	private Map<UUID, Map<String, Long>> cooldownMap = new HashMap<>();

	public Map<UUID, Map<String, Long>> getCooldownMap() {
		return cooldownMap;
	}

	public boolean hasAnyActiveCooldown(UUID uuid) {
		return cooldownMap.containsKey(uuid);
	}

	public Map<String, Long> getPlayerCooldowns(UUID uuid) {
		return cooldownMap.get(uuid);
	}

	public void addCooldown(UUID uuid, String type, long time) {
		if (cooldownMap.containsKey(uuid)) {
			cooldownMap.get(uuid).put(type, time);
			return;
		}
		Map<String, Long> tempCooldownMap = new HashMap<>();
		tempCooldownMap.put(type, time);
		cooldownMap.put(uuid, tempCooldownMap);
	}

	public void removeCooldown(UUID uuid, String type) {
		cooldownMap.get(uuid).remove(type);
	}
}
