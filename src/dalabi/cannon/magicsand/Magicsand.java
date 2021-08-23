package dalabi.cannon.magicsand;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.block.Block;

public class Magicsand {

	Block block;
	int material;
	byte data;
	int counter;

	public Magicsand(Block block, int material, byte data, int counter) {
		this.block = block;
		this.material = material;
		this.data = data;
		this.counter = counter;
	}

	private static final Map<UUID, Set<Magicsand>> MagicsandStorageMap = new ConcurrentHashMap<>();

	public static Map<UUID, Set<Magicsand>> getMagicsandStorageMap() {
		return MagicsandStorageMap;
	}

	public static Set<Magicsand> getMagicsandByPlayer(UUID uuid) {
		return MagicsandStorageMap.getOrDefault(uuid, null);
	}

	public Block getBlock() {
		return this.block;
	}

	public int getMaterial() {
		return this.material;
	}

	public byte getData() {
		return this.data;
	}

	public int getCounter() {
		return this.counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public static void putFirstMagicsand(UUID uuid, Set<Magicsand> ms) {
		MagicsandStorageMap.put(uuid, ms);
	}

	public static void addMagicsand(UUID uuid, Magicsand ms) {
		MagicsandStorageMap.get(uuid).add(ms);
	}
}
