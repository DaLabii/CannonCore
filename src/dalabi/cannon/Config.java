package dalabi.cannon;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

public class Config {

	private static Config config;

	public Config() {
		config = this;
	}

	public static Config getConfig() {
		return config;
	}

	public String cannoncore_reload_permission;
	public String cannoncore_reload_message;
	public String cannoncore_no_permission_message;
	public String cannoncore_invalid_args_message;

	public boolean magicsand_enabled;
	public String magicsand_use_permission;
	public String magicsand_bypass_refill_cooldown_permission;
	public String magicsand_bypass_clear_cooldown_permission;
	public String magicsand_no_permission_message;
	public String magicsand_invalid_args_message;
	public String magicsand_limit_reached_message;
	public String magicsand_refilled_message;
	public String magicsand_cleared_message;
	public String magicsand_refill_cooldown_message;
	public String magicsand_clear_cooldown_message;
	public List<String> magicsand_help_messages;
	public int magicsand_refill_cooldown;
	public int magicsand_clear_cooldown;
	public int magicsand_limit;
	public int magicsand_refill_limit;
	public Material magicsand_sand_item_material;
	public byte magicsand_sand_item_data;
	public String magicsand_sand_item_name;
	public List<String> magicsand_sand_item_lore;
	public Material magicsand_red_sand_item_material;
	public byte magicsand_red_sand_item_data;
	public String magicsand_red_sand_item_name;
	public List<String> magicsand_red_sand_item_lore;
	public Material magicsand_gravel_item_material;
	public byte magicsand_gravel_item_data;
	public String magicsand_gravel_item_name;
	public List<String> magicsand_gravel_item_lore;

	public boolean button_enabled;
	public String button_command_permission;
	public String button_pressed_message;
	public String button_not_found_message;
	public String button_no_permission_message;
	public String button_invalid_args_message;

	public boolean lever_enabled;
	public String lever_command_permission;
	public String lever_flicked_message;
	public String lever_not_found_message;
	public String lever_no_permission_message;
	public String lever_invalid_args_message;

	public boolean tick_counter_enabled;
	public String tick_counter_ticks_added_message;
	public String tick_counter_ticks_reset_message;
	public String tick_counter_given_message;

	public boolean build_helper_enabled;
	public String build_helper_command_permission;
	public String build_helper_no_permission_message;
	public String build_helper_enabled_message;
	public String build_helper_disabled_message;

	public void reload() {

		Main.getInstance().reloadConfig();

		this.cannoncore_reload_permission = Main.getInstance().getConfig().getString("cannoncore.permission.reload");
		this.cannoncore_reload_message = Main.getInstance().getConfig().getString("cannoncore.message.config_reloaded");
		this.cannoncore_no_permission_message = Main.getInstance().getConfig()
				.getString("cannoncore.message.no_permission");
		this.cannoncore_invalid_args_message = Main.getInstance().getConfig()
				.getString("cannoncore.message.invalid_args");

		this.magicsand_enabled = Main.getInstance().getConfig().getBoolean("magicsand.enabled");
		this.magicsand_use_permission = Main.getInstance().getConfig().getString("magicsand.permission.use");
		this.magicsand_bypass_refill_cooldown_permission = Main.getInstance().getConfig()
				.getString("magicsand.permission.bypass_refill_cooldown");
		this.magicsand_bypass_clear_cooldown_permission = Main.getInstance().getConfig()
				.getString("magicsand.permission.bypass_clear_cooldown");
		this.magicsand_no_permission_message = Main.getInstance().getConfig()
				.getString("magicsand.message.no_permission");
		this.magicsand_invalid_args_message = Main.getInstance().getConfig()
				.getString("magicsand.message.invalid_args");
		this.magicsand_limit_reached_message = Main.getInstance().getConfig()
				.getString("magicsand.message.limit_reached");
		this.magicsand_refilled_message = Main.getInstance().getConfig().getString("magicsand.message.refilled");
		this.magicsand_cleared_message = Main.getInstance().getConfig().getString("magicsand.message.cleared");
		this.magicsand_help_messages = new ArrayList<>();
		this.magicsand_help_messages.addAll(Main.getInstance().getConfig().getStringList("magicsand.message.help"));
		this.magicsand_refill_cooldown_message = Main.getInstance().getConfig()
				.getString("magicsand.message.refill_cooldown");
		this.magicsand_clear_cooldown_message = Main.getInstance().getConfig()
				.getString("magicsand.message.clear_cooldown");
		this.magicsand_refill_cooldown = Main.getInstance().getConfig().getInt("magicsand.cooldown.refill");
		this.magicsand_clear_cooldown = Main.getInstance().getConfig().getInt("magicsand.cooldown.clear");
		this.magicsand_limit = Main.getInstance().getConfig().getInt("magicsand.limit");
		this.magicsand_refill_limit = Main.getInstance().getConfig().getInt("magicsand.refill_limit");
		this.magicsand_sand_item_material = Material
				.getMaterial(Main.getInstance().getConfig().getString("magicsand.sand.item.material"));
		this.magicsand_sand_item_data = (byte) Main.getInstance().getConfig().getInt("magicsand.sand.item.data");
		this.magicsand_sand_item_name = Main.getInstance().getConfig().getString("magicsand.sand.item.name");
		this.magicsand_sand_item_lore = new ArrayList<>();
		this.magicsand_sand_item_lore.addAll(Main.getInstance().getConfig().getStringList("magicsand.sand.item.lore"));
		this.magicsand_red_sand_item_material = Material
				.getMaterial(Main.getInstance().getConfig().getString("magicsand.red_sand.item.material"));
		this.magicsand_red_sand_item_data = (byte) Main.getInstance().getConfig()
				.getInt("magicsand.red_sand.item.data");
		this.magicsand_red_sand_item_name = Main.getInstance().getConfig().getString("magicsand.red_sand.item.name");
		this.magicsand_red_sand_item_lore = new ArrayList<>();
		this.magicsand_red_sand_item_lore
				.addAll(Main.getInstance().getConfig().getStringList("magicsand.red_sand.item.lore"));
		this.magicsand_gravel_item_material = Material
				.getMaterial(Main.getInstance().getConfig().getString("magicsand.gravel.item.material"));
		this.magicsand_gravel_item_data = (byte) Main.getInstance().getConfig().getInt("magicsand.gravel.item.data");
		this.magicsand_gravel_item_name = Main.getInstance().getConfig().getString("magicsand.gravel.item.name");
		this.magicsand_gravel_item_lore = new ArrayList<>();
		this.magicsand_gravel_item_lore
				.addAll(Main.getInstance().getConfig().getStringList("magicsand.gravel.item.lore"));

		this.button_enabled = Main.getInstance().getConfig().getBoolean("button.enabled");
		this.button_command_permission = Main.getInstance().getConfig().getString("button.permission.use");
		this.button_pressed_message = Main.getInstance().getConfig().getString("button.message.pressed");
		this.button_not_found_message = Main.getInstance().getConfig().getString("button.message.not_found");
		this.button_no_permission_message = Main.getInstance().getConfig().getString("button.message.no_permission");
		this.button_invalid_args_message = Main.getInstance().getConfig().getString("button.message.invalid_args");

		this.lever_enabled = Main.getInstance().getConfig().getBoolean("lever.enabled");
		this.lever_command_permission = Main.getInstance().getConfig().getString("lever.permission.use");
		this.lever_flicked_message = Main.getInstance().getConfig().getString("lever.message.flicked");
		this.lever_not_found_message = Main.getInstance().getConfig().getString("lever.message.not_found");
		this.lever_no_permission_message = Main.getInstance().getConfig().getString("lever.message.no_permission");
		this.lever_invalid_args_message = Main.getInstance().getConfig().getString("lever.message.invalid_args");

		this.tick_counter_enabled = Main.getInstance().getConfig().getBoolean("tick_counter.enabled");
		this.tick_counter_ticks_added_message = Main.getInstance().getConfig().getString("tick_counter.message.added");
		this.tick_counter_ticks_reset_message = Main.getInstance().getConfig().getString("tick_counter.message.reset");
		this.tick_counter_given_message = Main.getInstance().getConfig().getString("tick_counter.message.given");

		this.build_helper_enabled = Main.getInstance().getConfig().getBoolean("build_helper.enabled");
		this.build_helper_command_permission = Main.getInstance().getConfig().getString("build_helper.permission.use");
		this.build_helper_no_permission_message = Main.getInstance().getConfig()
				.getString("build_helper.message.no_permission");
		this.build_helper_enabled_message = Main.getInstance().getConfig().getString("build_helper.message.enabled");
		this.build_helper_disabled_message = Main.getInstance().getConfig().getString("build_helper.message.disabled");
	}
}
