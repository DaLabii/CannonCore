package dalabi.cannon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.block.Block;

import dalabi.cannon.magicsand.Magicsand;

public class Storage {

	private static Storage storage;

	public Storage() {
		storage = this;
	}

	public static Storage getStorage() {
		return storage;
	}

//#################################################################################
	// map of all buttons for players
	private final Map<UUID, Block> buttonMap = new HashMap<>();

	public Block getButtonBlock(UUID uuid) {
		return this.buttonMap.getOrDefault(uuid, null);
	}

	public void setButtonBlock(UUID uuid, Block block) {
		this.buttonMap.put(uuid, block);
	}

//#################################################################################
	// map of all levers for players
	private final Map<UUID, Block> leverMap = new HashMap<>();

	public Block getLeverBlock(UUID uuid) {
		return this.leverMap.getOrDefault(uuid, null);
	}

	public void setLeverBlock(UUID uuid, Block block) {
		this.leverMap.put(uuid, block);
	}

//#################################################################################
	// list of all magicsand for players
	private List<Magicsand> magicsandList = new ArrayList<>();

	public List<Magicsand> getMagicsandList() {
		return this.magicsandList;
	}

	public void addToMagicsand(Magicsand ms) {
		this.magicsandList.add(ms);
	}

	public void removeMagicsand(Magicsand ms) {
		this.magicsandList.remove(ms);
	}
//#################################################################################
	// map of magicsand amounts per player

	private Map<UUID, Integer> magicsandCountMap = new HashMap<>();

	public boolean hasMagicsand(UUID uuid) {
		return this.magicsandCountMap.containsKey(uuid);
	}

	public int getMagicsandAmount(UUID uuid) {
		return this.magicsandCountMap.get(uuid);
	}

	public void setMagicsandAmount(UUID uuid, int count) {
		this.magicsandCountMap.put(uuid, count);
	}

//#################################################################################
	// map of all magicsand thats gonna be cleared
	private final Map<Block, Integer> magicsandClearableMap = new ConcurrentHashMap<>();

	public Map<Block, Integer> getClearableMap() {
		return this.magicsandClearableMap;
	}

	public int getClearableCounter(Block block) {
		return this.magicsandClearableMap.get(block);
	}

	public void addToClearable(Block block) {
		this.magicsandClearableMap.put(block, 1);
	}

	public void removeFromClearable(Block block) {
		this.magicsandClearableMap.remove(block);
	}

	public void setClearableCounter(Block block, int count) {
		this.magicsandClearableMap.put(block, count);
	}

// #################################################################################
	// list of all removable magicsand
	private List<Magicsand> magicsandRemovableMap = new ArrayList<>();

	public List<Magicsand> getRemovable() {
		return this.magicsandRemovableMap;
	}

	public void addToRemovable(Magicsand block) {
		this.magicsandRemovableMap.add(block);
	}

	public void clearRemovable() {
		this.magicsandRemovableMap.clear();
	}

//#################################################################################
	// set of serialized magicsand block locations
	private Set<String> magicsandBlockSet = new HashSet<>();

	public boolean isBlockInMagicsandBlockSet(Block block) {
		return this.magicsandBlockSet.contains(GeneralUtil.serializeBlock(block));
	}

	public void addToMagicsandBlockSet(Block block) {
		this.magicsandBlockSet.add(GeneralUtil.serializeBlock(block));
	}

	public void removeFromMagicsandBlockSet(Block block) {
		this.magicsandBlockSet.remove(GeneralUtil.serializeBlock(block));
	}

//#################################################################################
	// map of all ticks for players
	private Map<UUID, Integer> tickCounterMap = new HashMap<>();

	public boolean hasTicks(UUID uuid) {
		return this.tickCounterMap.containsKey(uuid);
	}

	public int getTicks(UUID uuid) {
		return this.tickCounterMap.getOrDefault(uuid, null);
	}

	public void setTicks(UUID uuid, int count) {
		this.tickCounterMap.put(uuid, count);
	}

	public void resetTicks(UUID uuid) {
		this.tickCounterMap.remove(uuid);
	}
//#################################################################################

	// set of UUID's for buildhelper
	private Set<UUID> buildHelperSet = new HashSet<>();

	public boolean isUUIDinBuildhelperSet(UUID uuid) {
		return this.buildHelperSet.contains(uuid);
	}

	public void addToBuildhelperSet(UUID uuid) {
		this.buildHelperSet.add(uuid);
	}

	public void removeFromBuildhelperSet(UUID uuid) {
		this.buildHelperSet.remove(uuid);
	}
}
