package dalabi.cannon;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

public class Config {

	public static String cannoncore_reload_permission;
	public static String cannoncore_reload_message;
	public static String cannoncore_no_permission_message;
	public static String cannoncore_invalid_args_message;

	public static boolean magicsand_enabled;
	public static String magicsand_use_permission;
	public static String magicsand_bypass_refill_cooldown_permission;
	public static String magicsand_bypass_clear_cooldown_permission;
	public static String magicsand_no_permission_message;
	public static String magicsand_invalid_args_message;
	public static String magicsand_limit_reached_message;
	public static String magicsand_refilled_message;
	public static String magicsand_cleared_message;
	public static String magicsand_refill_cooldown_message;
	public static String magicsand_clear_cooldown_message;
	public static int magicsand_refill_cooldown;
	public static int magicsand_clear_cooldown;
	public static int magicsand_limit;
	public static Material magicsand_sand_item_material;
	public static byte magicsand_sand_item_data;
	public static String magicsand_sand_item_name;
	public static List<String> magicsand_sand_item_lore = new ArrayList<>();
	public static Material magicsand_red_sand_item_material;
	public static byte magicsand_red_sand_item_data;
	public static String magicsand_red_sand_item_name;
	public static List<String> magicsand_red_sand_item_lore = new ArrayList<>();
	public static Material magicsand_gravel_item_material;
	public static byte magicsand_gravel_item_data;
	public static String magicsand_gravel_item_name;
	public static List<String> magicsand_gravel_item_lore = new ArrayList<>();

	public static boolean button_enabled;
	public static String button_command_permission;
	public static String button_pressed_message;
	public static String button_not_found_message;
	public static String button_no_permission_message;
	public static String button_invalid_args_message;
	
	public static boolean lever_enabled;
	public static String lever_command_permission;
	public static String lever_flicked_message;
	public static String lever_not_found_message;
	public static String lever_no_permission_message;
	public static String lever_invalid_args_message;

	public static void reload() {
		
		Main.getInstance().reloadConfig();
		
		cannoncore_reload_permission = Main.getInstance().getConfig().getString("cannoncore.permission.reload");
		cannoncore_reload_message = Main.getInstance().getConfig().getString("cannoncore.message.config_reloaded");
		cannoncore_no_permission_message = Main.getInstance().getConfig().getString("cannoncore.message.no_permission");
		cannoncore_invalid_args_message = Main.getInstance().getConfig().getString("cannoncore.message.invalid_args");
		magicsand_enabled = Main.getInstance().getConfig().getBoolean("magicsand.enabled");
		magicsand_use_permission = Main.getInstance().getConfig().getString("magicsand.permission.use");
		magicsand_bypass_refill_cooldown_permission = Main.getInstance().getConfig()
				.getString("magicsand.permission.bypass_refill_cooldown");
		magicsand_bypass_clear_cooldown_permission = Main.getInstance().getConfig()
				.getString("magicsand.permission.bypass_clear_cooldown");
		magicsand_no_permission_message = Main.getInstance().getConfig().getString("magicsand.message.no_permission");
		magicsand_invalid_args_message = Main.getInstance().getConfig().getString("magicsand.message.invalid_args");
		magicsand_limit_reached_message = Main.getInstance().getConfig().getString("magicsand.message.limit_reached");
		magicsand_refilled_message = Main.getInstance().getConfig().getString("magicsand.message.refilled");
		magicsand_cleared_message = Main.getInstance().getConfig().getString("magicsand.message.cleared");
		magicsand_refill_cooldown_message = Main.getInstance().getConfig()
				.getString("magicsand.message.refill_cooldown");
		magicsand_clear_cooldown_message = Main.getInstance().getConfig().getString("magicsand.message.clear_cooldown");
		magicsand_refill_cooldown = Main.getInstance().getConfig().getInt("magicsand.cooldown.refill");
		magicsand_clear_cooldown = Main.getInstance().getConfig().getInt("magicsand.cooldown.clear");
		magicsand_limit = Main.getInstance().getConfig().getInt("magicsand.limit");
		magicsand_sand_item_material = Material
				.getMaterial(Main.getInstance().getConfig().getString("magicsand.sand.item.material"));
		magicsand_sand_item_data = (byte) Main.getInstance().getConfig().getInt("magicsand.sand.item.data");
		magicsand_sand_item_name = Main.getInstance().getConfig().getString("magicsand.sand.item.name");
		magicsand_sand_item_lore.addAll(Main.getInstance().getConfig().getStringList("magicsand.sand.item.lore"));
		magicsand_red_sand_item_material = Material
				.getMaterial(Main.getInstance().getConfig().getString("magicsand.red_sand.item.material"));
		magicsand_red_sand_item_data = (byte) Main.getInstance().getConfig().getInt("magicsand.red_sand.item.data");
		magicsand_red_sand_item_name = Main.getInstance().getConfig().getString("magicsand.red_sand.item.name");
		magicsand_red_sand_item_lore
				.addAll(Main.getInstance().getConfig().getStringList("magicsand.red_sand.item.lore"));
		magicsand_gravel_item_material = Material
				.getMaterial(Main.getInstance().getConfig().getString("magicsand.gravel.item.material"));
		magicsand_gravel_item_data = (byte) Main.getInstance().getConfig().getInt("magicsand.gravel.item.data");
		magicsand_gravel_item_name = Main.getInstance().getConfig().getString("magicsand.gravel.item.name");
		magicsand_gravel_item_lore.addAll(Main.getInstance().getConfig().getStringList("magicsand.gravel.item.lore"));
		
		button_enabled = Main.getInstance().getConfig().getBoolean("button.enabled");
		button_command_permission = Main.getInstance().getConfig().getString("button.permission.use");
		button_pressed_message = Main.getInstance().getConfig().getString("button.message.pressed");
		button_not_found_message = Main.getInstance().getConfig().getString("button.message.not_found");
		button_no_permission_message = Main.getInstance().getConfig().getString("button.message.no_permission");
		button_invalid_args_message = Main.getInstance().getConfig().getString("button.message.invalid_args");
		
		lever_enabled = Main.getInstance().getConfig().getBoolean("lever.enabled");
		lever_command_permission = Main.getInstance().getConfig().getString("lever.permission.use");
		lever_flicked_message = Main.getInstance().getConfig().getString("lever.message.flicked");
		lever_not_found_message = Main.getInstance().getConfig().getString("lever.message.not_found");
		lever_no_permission_message = Main.getInstance().getConfig().getString("lever.message.no_permission");
		lever_invalid_args_message = Main.getInstance().getConfig().getString("lever.message.invalid_args");
	}
}
