package dalabi.cannon;

import org.bukkit.plugin.java.JavaPlugin;

import dalabi.cannon.fire.FireCMD;
import dalabi.cannon.fire.FireEvents;
import dalabi.cannon.lever.LeverCMD;
import dalabi.cannon.lever.LeverEvents;
import dalabi.cannon.magicsand.MagicsandCMD;
import dalabi.cannon.magicsand.MagicsandEvents;
import dalabi.cannon.magicsand.MagicsandRefillCMD;
import dalabi.cannon.magicsand.MagicsandUtil;

public class Main extends JavaPlugin {
	private static Main main;

	public void onEnable() {
		Main.main = this;
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		Config.reload();
		getCommand("cannoncore").setExecutor(new CannonCoreCMD());
		if (Config.magicsand_enabled) {
			getCommand("refill").setExecutor(new MagicsandRefillCMD());
			getCommand("ms").setExecutor(new MagicsandCMD());
			getServer().getPluginManager().registerEvents(new MagicsandEvents(), this);
			MagicsandUtil.startMagicsandTasks();
		}
		if (Config.button_enabled) {
			getCommand("fire").setExecutor(new FireCMD());
			getServer().getPluginManager().registerEvents(new FireEvents(), this);
		}
		if (Config.lever_enabled) {
			getCommand("lever").setExecutor(new LeverCMD());
			getServer().getPluginManager().registerEvents(new LeverEvents(), this);
		}
	}

	public static Main getInstance() {
		return main;
	}

}
