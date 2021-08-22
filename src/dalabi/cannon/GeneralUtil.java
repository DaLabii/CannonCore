package dalabi.cannon;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class GeneralUtil {

	public static void sendMSG(Player player, String msg) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
	}

	public static ItemStack MakeItemStack(Material Type, int Amt, int Meta, String DName, List<String> Lore) {
		final ItemStack itemStack = new ItemStack(Type, Amt, (short) Meta);
		final ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', DName));
		itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		itemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		final List<String> ColorCodedLore = new ArrayList<>();
		for (final String CurLore : Lore) {
			ColorCodedLore.add(ChatColor.translateAlternateColorCodes('&', CurLore));
		}
		itemMeta.setLore(ColorCodedLore);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
}
