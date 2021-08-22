package dalabi.cannon.magicsand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import dalabi.cannon.Config;
import dalabi.cannon.CooldownManager;
import dalabi.cannon.GeneralUtil;
import dalabi.cannon.Main;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.IBlockData;

public class MagicsandUtil {
	public static boolean hasClearCooldown(Player player) {
		if (!CooldownManager.hasActiveCooldown(player)) {
			return false;
		}
		if (CooldownManager.getPlayerCooldowns(player).containsKey("magicsand_clear")) {
			long currentTime = System.currentTimeMillis();
			if ((currentTime - CooldownManager.getPlayerCooldowns(player).get("magicsand_clear"))
					/ 1000 >= Config.magicsand_clear_cooldown
					|| player.hasPermission(Config.magicsand_bypass_clear_cooldown_permission)) {
				return false;
			}
			GeneralUtil.sendMSG(player, Config.magicsand_clear_cooldown_message);
			return true;
		}
		return false;
	}

	public static void clearMagicsand(Player player) {
		if (Magicsand.getMagicsandByPlayer(player) == null) {
			return;
		}
		for (Magicsand magicsand : Magicsand.getMagicsandByPlayer(player)) {
			Block block = magicsand.getBlock();
			if (!block.hasMetadata("magicsand")) {
				continue;
			}
			block.removeMetadata("magicsand", Main.getInstance());
			MagicsandClearable.addToClearable(block);
		}
		GeneralUtil.sendMSG(player, Config.magicsand_cleared_message);
		Map<String, Long> cooldown = new HashMap<>();
		cooldown.put("magicsand_clear", System.currentTimeMillis());
		CooldownManager.putCooldown(player, cooldown);
	}

	public static boolean hasRefillCooldown(Player player) {
		if (!CooldownManager.hasActiveCooldown(player)) {
			return false;
		}
		if (CooldownManager.getPlayerCooldowns(player).containsKey("magicsand_refill")) {
			long currentTime = System.currentTimeMillis();
			if ((currentTime - CooldownManager.getPlayerCooldowns(player).get("magicsand_refill"))
					/ 1000 >= Config.magicsand_refill_cooldown
					|| player.hasPermission(Config.magicsand_bypass_refill_cooldown_permission)) {

				return false;
			}
			GeneralUtil.sendMSG(player, Config.magicsand_refill_cooldown_message);
			return true;
		}
		return false;
	}

	public static void refillMagicsand(Player player) {
		int refilled = 0;
		if (Magicsand.getMagicsandByPlayer(player) == null) {
			refilled = scanBlocks(player.getLocation(), player, Config.magicsand_limit - 0);
		} else {
			refilled = scanBlocks(player.getLocation(), player,
					Config.magicsand_limit - Magicsand.getMagicsandByPlayer(player).size());
		}
		GeneralUtil.sendMSG(player, Config.magicsand_refilled_message.replace("%count%", String.valueOf(refilled)));
		Map<String, Long> cooldown = new HashMap<>();
		cooldown.put("magicsand_refill", System.currentTimeMillis());
		CooldownManager.putCooldown(player, cooldown);
	}

	@SuppressWarnings("deprecation")
	public static int scanBlocks(Location center, Player player, int untilLimit) {
		int count = 0;
		for (int x = center.getBlockX() - 25; x <= center.getBlockX() + 25; x++) {
			for (int z = center.getBlockZ() - 25; z <= center.getBlockZ() + 25; z++) {
				for (int y = center.getBlockY() - 25; y <= center.getBlockY() + 25; y++) {
					if (count >= 100 || count >= untilLimit) {
						return count;
					}
					Block block = Bukkit.getWorld(center.getWorld().getName()).getBlockAt(x, y, z);
					Material material = block.getType();
					if (material != Config.magicsand_sand_item_material
							&& material != Config.magicsand_red_sand_item_material
							&& material != Config.magicsand_gravel_item_material) {
						continue;
					}
					if (block.hasMetadata("magicsand")) {
						continue;
					}
					byte data = translateMagicsandItemDataToBlockData(block.getData());
					if (data == 99) {
						continue;
					}
					if (Magicsand.getMagicsandByPlayer(player) == null) {
						Set<Magicsand> msSet = new HashSet<>();
						Magicsand magicsand = new Magicsand(player, block,
								translateMagicsandItemDataToMaterialID(block.getData()),
								translateMagicsandItemDataToBlockData(block.getData()), 1);
						msSet.add(magicsand);
						Magicsand.putFirstMagicsand(player, msSet);
					} else {
						Magicsand magicsand = new Magicsand(player, block,
								translateMagicsandItemDataToMaterialID(block.getData()),
								MagicsandUtil.translateMagicsandItemDataToBlockData(block.getData()), 1);
						Magicsand.addMagicsand(player, magicsand);
					}
					block.setMetadata("magicsand", new FixedMetadataValue(Main.getInstance(), "cannoncore"));
					count++;

				}
			}
		}
		return count;
	}

	@SuppressWarnings("deprecation")
	public static void startMagicsandTasks() {
		// iterates through placed magicsand and checks if the block below is
		// air/water/lava
		// and sets the sand
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

			public void run() {

				for (Player player : Magicsand.getMagicsandStorageMap().keySet()) {
					if (!Bukkit.getOnlinePlayers().contains(player)) {
						continue;
					}
					for (Magicsand magicsand : Magicsand.getMagicsandByPlayer(player)) {
						if (!magicsand.getBlock().hasMetadata("magicsand")) {
							if (MagicsandRemovable.containsPlayer(player)) {
								MagicsandRemovable.addToRemovable(player, magicsand);
							} else {
								List<Magicsand> removables = new ArrayList<>();
								removables.add(magicsand);
								MagicsandRemovable.putInRemovable(player, removables);
							}
							continue;
						}
						Material material = magicsand.getBlock().getType();
						if (material != Config.magicsand_sand_item_material
								&& material != Config.magicsand_red_sand_item_material
								&& material != Config.magicsand_gravel_item_material) {
							magicsand.getBlock().setType(Material.SAND);
							magicsand.getBlock().setData((byte) 0);
						}
						Block block = magicsand.getBlock();
						Material b = block.getRelative(0, -magicsand.getCounter(), 0).getType();
						if (b == Material.AIR || b == Material.WATER || b == Material.LAVA) {
							setBlockInNativeChunk(block.getWorld(), block.getX(), block.getY() - magicsand.getCounter(),
									block.getZ(), magicsand.getMaterial(), magicsand.getData());
							block.getRelative(0, -magicsand.getCounter(), 0).getState().update();
							magicsand.setCounter(magicsand.getCounter() + 1);
						} else {
							magicsand.setCounter(1);
						}
					}
				}
				for (Player player : MagicsandRemovable.getRemovable().keySet()) {
					for (Magicsand block : MagicsandRemovable.getRemovable().get(player)) {
						try {
							Magicsand.getMagicsandByPlayer(player).remove(block);
						} catch (Exception e) {
						}
					}
				}
				MagicsandRemovable.clearRemovable();
				for (Block block : MagicsandClearable.getClearableMap().keySet()) {
					Block forClear = block.getRelative(0, -MagicsandClearable.getCounter(block), 0);
					if (forClear.getType() == Material.GRAVEL || forClear.getType() == Material.SAND) {
						setBlockInNativeChunk(forClear.getWorld(), forClear.getX(), forClear.getY(), forClear.getZ(), 0,
								(byte) 0);
						forClear.getState().update();
						MagicsandClearable.setCounter(block, MagicsandClearable.getCounter(block) + 1);
					} else {
						MagicsandClearable.removeFromClearable(block);
					}
				}
			}
		}, 1, 1);
	}

	public static byte translateMagicsandItemDataToBlockData(byte data) {
		if (data == Config.magicsand_sand_item_data) {
			return 0;
		}
		if (data == Config.magicsand_red_sand_item_data) {
			return 1;
		}
		if (data == Config.magicsand_gravel_item_data) {
			return 0;
		}
		return 99;
	}

	public static int translateMagicsandItemDataToMaterialID(byte data) {
		if (data == Config.magicsand_sand_item_data) {
			return 12;
		}
		if (data == Config.magicsand_red_sand_item_data) {
			return 12;	
		}
		if (data == Config.magicsand_gravel_item_data) {
			return 13;
		}
		return 12;
	}

	public static void setBlockInNativeChunk(World world, int x, int y, int z, int blockId, byte data) {
		net.minecraft.server.v1_8_R3.World nmsWorld = ((CraftWorld) world).getHandle();
		net.minecraft.server.v1_8_R3.Chunk nmsChunk = nmsWorld.getChunkAt(x >> 4, z >> 4);
		BlockPosition bp = new BlockPosition(x, y, z);
		IBlockData ibd = net.minecraft.server.v1_8_R3.Block.getByCombinedId(blockId + (data << 12));
		nmsChunk.a(bp, ibd);
	}
}
