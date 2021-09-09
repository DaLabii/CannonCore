package dalabi.cannon.magicsand;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;

import dalabi.cannon.Config;
import dalabi.cannon.CooldownManager;
import dalabi.cannon.GeneralUtil;
import dalabi.cannon.Main;
import dalabi.cannon.Storage;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.Chunk;
import net.minecraft.server.v1_8_R3.ChunkSection;
import net.minecraft.server.v1_8_R3.IBlockData;
import net.minecraft.server.v1_8_R3.WorldServer;

public class MagicsandUtil {

	public static boolean hasClearCooldown(Player player) {
		if (!CooldownManager.getCooldownManager().hasAnyActiveCooldown(player.getUniqueId())) {
			return false;
		}
		if (CooldownManager.getCooldownManager().getPlayerCooldowns(player.getUniqueId())
				.containsKey("magicsand_clear")) {
			long currentTime = System.currentTimeMillis();
			if ((currentTime - CooldownManager.getCooldownManager().getPlayerCooldowns(player.getUniqueId())
					.get("magicsand_clear")) / 1000 >= Config.getConfig().magicsand_clear_cooldown
					|| player.hasPermission(Config.getConfig().magicsand_bypass_clear_cooldown_permission)) {
				return false;
			}
			GeneralUtil.sendMessage(player, Config.getConfig().magicsand_clear_cooldown_message);
			return true;
		}
		return false;
	}

	public static void clearMagicsand(Player player) {
		if (!Storage.getStorage().hasMagicsand(player.getUniqueId())) {
			return;
		}
		for (Magicsand magicsand : Storage.getStorage().getMagicsandList()) {
			if (player.getUniqueId() != magicsand.getOwner()) {
				continue;
			}
			Block block = magicsand.getBlock();
			Storage.getStorage().addToRemovable(magicsand);
			Storage.getStorage().addToClearable(block);
		}
		GeneralUtil.sendMessage(player, Config.getConfig().magicsand_cleared_message);
		CooldownManager.getCooldownManager().addCooldown(player.getUniqueId(), "magicsand_clear",
				System.currentTimeMillis());
	}

	public static boolean hasRefillCooldown(Player player) {
		if (!CooldownManager.getCooldownManager().hasAnyActiveCooldown(player.getUniqueId())) {
			return false;
		}
		if (CooldownManager.getCooldownManager().getPlayerCooldowns(player.getUniqueId())
				.containsKey("magicsand_refill")) {
			long currentTime = System.currentTimeMillis();
			if ((currentTime - CooldownManager.getCooldownManager().getPlayerCooldowns(player.getUniqueId())
					.get("magicsand_refill")) / 1000 >= Config.getConfig().magicsand_refill_cooldown
					|| player.hasPermission(Config.getConfig().magicsand_bypass_refill_cooldown_permission)) {

				return false;
			}
			GeneralUtil.sendMessage(player, Config.getConfig().magicsand_refill_cooldown_message);
			return true;
		}
		return false;
	}

	public static void refillMagicsand(Player player) {
		int refilled = 0;
		if (!Storage.getStorage().hasMagicsand(player.getUniqueId())) {
			refilled = scanBlocks(player.getLocation(), player, Config.getConfig().magicsand_limit - 0);
		} else {
			refilled = scanBlocks(player.getLocation(), player,
					Config.getConfig().magicsand_limit - Storage.getStorage().getMagicsandAmount(player.getUniqueId()));
		}
		GeneralUtil.sendMessage(player,
				Config.getConfig().magicsand_refilled_message.replace("%count%", String.valueOf(refilled)));
		CooldownManager.getCooldownManager().addCooldown(player.getUniqueId(), "magicsand_refill",
				System.currentTimeMillis());
	}

	@SuppressWarnings("deprecation")
	public static int scanBlocks(Location center, Player player, int untilLimit) {
		int count = 0;
		for (int x = center.getBlockX() - 25; x <= center.getBlockX() + 25; x++) {
			for (int z = center.getBlockZ() - 25; z <= center.getBlockZ() + 25; z++) {
				for (int y = center.getBlockY() - 25; y <= center.getBlockY() + 25; y++) {
					if (count >= Config.getConfig().magicsand_refill_limit || count >= untilLimit) {
						return count;
					}
					Block block = Bukkit.getWorld(center.getWorld().getName()).getBlockAt(x, y, z);
					Material material = block.getType();
					if (material != Config.getConfig().magicsand_sand_item_material
							&& material != Config.getConfig().magicsand_red_sand_item_material
							&& material != Config.getConfig().magicsand_gravel_item_material) {
						continue;
					}
					if (Storage.getStorage().isBlockInMagicsandBlockSet(block)) {
						continue;
					}
					byte data = translateMagicsandItemDataToBlockData(block.getData());
					if (data == 99) {
						continue;
					}
					Magicsand magicsand = new Magicsand(block, translateMagicsandItemDataToMaterialID(block.getData()),
							translateMagicsandItemDataToBlockData(block.getData()), 1, player.getUniqueId());
					Storage.getStorage().addToMagicsand(magicsand);
					Storage.getStorage().addToMagicsandBlockSet(block);
					if (!Storage.getStorage().hasMagicsand(player.getUniqueId())) {
						Storage.getStorage().setMagicsandAmount(player.getUniqueId(), 1);
					} else {
						int currentAmount = Storage.getStorage().getMagicsandAmount(player.getUniqueId());
						Storage.getStorage().setMagicsandAmount(player.getUniqueId(), currentAmount + 1);
					}
					count++;

				}
			}
		}
		return count;
	}

	public static void startMagicsandTasks() {
		// iterates through placed magicsand and checks if the block below is
		// air/water/lava
		// and sets the sand/gravel
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

			public void run() {
				for (Magicsand magicsand : Storage.getStorage().getMagicsandList()) {
					if (!Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(magicsand.getOwner()))) {
						continue;
					}
					Material material = magicsand.getBlock().getType();
					if (material != Config.getConfig().magicsand_sand_item_material
							&& material != Config.getConfig().magicsand_red_sand_item_material
							&& material != Config.getConfig().magicsand_gravel_item_material) {
						Storage.getStorage().addToRemovable(magicsand);
						int currentCount = Storage.getStorage().getMagicsandAmount(magicsand.getOwner());
						Storage.getStorage().setMagicsandAmount(magicsand.getOwner(), currentCount - 1);
						continue;
					}
					Block block = magicsand.getBlock();
					Material b = block.getRelative(0, -magicsand.getCounter(), 0).getType();
					if (b == Material.AIR || b == Material.WATER || b == Material.LAVA) {
						setBlock(block.getWorld(), block.getX(), block.getY() - magicsand.getCounter(), block.getZ(),
								magicsand.getMaterial(), magicsand.getData());
						magicsand.setCounter(magicsand.getCounter() + 1);
					} else {
						magicsand.setCounter(1);
					}
				}
				for (Magicsand magicsand : Storage.getStorage().getRemovable()) {
					try {
						Storage.getStorage().removeMagicsand(magicsand);
						Storage.getStorage().removeFromMagicsandBlockSet(magicsand.getBlock());
					} catch (Exception e) {

					}
				}
				Storage.getStorage().clearRemovable();
				for (Block block : Storage.getStorage().getClearableMap().keySet()) {
					Block clearableBlock = block.getRelative(0, -Storage.getStorage().getClearableCounter(block), 0);
					if (clearableBlock.getType() == Material.GRAVEL || clearableBlock.getType() == Material.SAND) {
						setBlock(clearableBlock.getWorld(), clearableBlock.getX(), clearableBlock.getY(),
								clearableBlock.getZ(), 0, (byte) 0);
						Storage.getStorage().setClearableCounter(block,
								Storage.getStorage().getClearableCounter(block) + 1);
					} else {
						Storage.getStorage().removeFromClearable(block);
					}
				}
			}
		}, 1, 1);
	}

	public static byte translateMagicsandItemDataToBlockData(byte data) {
		if (data == Config.getConfig().magicsand_sand_item_data) {
			return 0;
		}
		if (data == Config.getConfig().magicsand_red_sand_item_data) {
			return 1;
		}
		if (data == Config.getConfig().magicsand_gravel_item_data) {
			return 0;
		}
		return 99;
	}

	public static int translateMagicsandItemDataToMaterialID(byte data) {
		if (data == Config.getConfig().magicsand_sand_item_data) {
			return 12;
		}
		if (data == Config.getConfig().magicsand_red_sand_item_data) {
			return 12;
		}
		if (data == Config.getConfig().magicsand_gravel_item_data) {
			return 13;
		}
		return 12;
	}

	/*
	 * not my work but its fastest method i have found so far for block placement in 1.8
	 */
	public static void setBlock(World world, int x, int y, int z, int id, byte data) {
		WorldServer worldServer = ((CraftWorld) world).getHandle();
		Chunk nmsChunk = worldServer.getChunkAt(x >> 4, z >> 4);
		IBlockData iBlockData = net.minecraft.server.v1_8_R3.Block.getByCombinedId(id + (data << 12));
		BlockPosition position = new BlockPosition(x, y, z);
		ChunkSection chunksection = nmsChunk.getSections()[position.getY() >> 4];
		if (chunksection == null)
			chunksection = nmsChunk.getSections()[position.getY() >> 4] = new ChunkSection(position.getY() >> 4 << 4,
					!(nmsChunk.getWorld()).worldProvider.o());
		chunksection.setType(position.getX() & 0xF, position.getY() & 0xF, position.getZ() & 0xF, iBlockData);
		worldServer.notify(position);
	}

}
