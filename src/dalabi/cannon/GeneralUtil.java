package dalabi.cannon;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class GeneralUtil {

	public static void sendMessage(Player player, String msg) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
	}

	// translates a block location into a string
	public static String serializeBlock(Block block) {
		return block.getWorld() + "," + block.getX() + "," + block.getY() + "," + block.getZ();
	}

	public static Block deserializeBlock(String string) {
		int x = 0;
		int y = 0;
		int z = 0;
		try {
			x = Integer.parseInt(string.split(",")[1]);
			y = Integer.parseInt(string.split(",")[2]);
			z = Integer.parseInt(string.split(",")[3]);
		} catch (Exception e) {
			System.err.println(e);
		}
		return Bukkit.getWorld(string.split(",")[0]).getBlockAt(x, y, z);
	}

	public static ItemStack MakeItemStack(Material Type, int Amt, int Meta, String DName, List<String> Lore) {
		ItemStack itemStack = new ItemStack(Type, Amt, (short) Meta);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', DName));
		itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		itemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		List<String> ColorCodedLore = new ArrayList<>();
		for (final String CurLore : Lore) {
			ColorCodedLore.add(ChatColor.translateAlternateColorCodes('&', CurLore));
		}
		itemMeta.setLore(ColorCodedLore);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
}
