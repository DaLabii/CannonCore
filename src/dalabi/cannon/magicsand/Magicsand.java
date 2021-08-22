package dalabi.cannon.magicsand;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Magicsand {
	
	Player player;
	Block block;
	int material;
	byte data;
	int counter;

	public Magicsand(Player player, Block block, int material, byte data, int counter) {
		this.player = player;
		this.block = block;
		this.material = material;
		this.data = data;
		this.counter = counter;
	}

	private static final Map<Player, Set<Magicsand>> MagicsandStorageMap = new ConcurrentHashMap<>();

	public static Map<Player, Set<Magicsand>> getMagicsandStorageMap() {
		return MagicsandStorageMap;
	}

	public static Set<Magicsand> getMagicsandByPlayer(Player player) {
		return MagicsandStorageMap.getOrDefault(player, null);
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

	public static void putFirstMagicsand(Player player, Set<Magicsand> ms) {
		MagicsandStorageMap.put(player, ms);
	}

	public static void addMagicsand(Player player, Magicsand ms) {
		MagicsandStorageMap.get(player).add(ms);
	}
}
