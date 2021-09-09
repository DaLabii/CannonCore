package dalabi.cannon;

import org.bukkit.plugin.java.JavaPlugin;

import dalabi.cannon.buildhelper.BuildHelperCMD;
import dalabi.cannon.buildhelper.BuildHelperEvents;
import dalabi.cannon.fire.FireCMD;
import dalabi.cannon.fire.FireEvents;
import dalabi.cannon.lever.LeverCMD;
import dalabi.cannon.lever.LeverEvents;
import dalabi.cannon.magicsand.MagicsandCMD;
import dalabi.cannon.magicsand.MagicsandEvents;
import dalabi.cannon.magicsand.MagicsandRefillCMD;
import dalabi.cannon.magicsand.MagicsandUtil;
import dalabi.cannon.tickcounter.TickCounterCMD;
import dalabi.cannon.tickcounter.TickCounterEvents;

public class Main extends JavaPlugin {
	
	private static Main main;

	public void onEnable() {
		Main.main = this;
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		new Config();
		new Storage();
		new CooldownManager();
		Config.getConfig().reload();
		getCommand("cannoncore").setExecutor(new CannonCoreCMD());
		if (Config.getConfig().magicsand_enabled) {
			getCommand("refill").setExecutor(new MagicsandRefillCMD());
			getCommand("ms").setExecutor(new MagicsandCMD());
			getServer().getPluginManager().registerEvents(new MagicsandEvents(), this);
			MagicsandUtil.startMagicsandTasks();
		}
		if (Config.getConfig().button_enabled) {
			getCommand("fire").setExecutor(new FireCMD());
			getServer().getPluginManager().registerEvents(new FireEvents(), this);
		}
		if (Config.getConfig().lever_enabled) {
			getCommand("lever").setExecutor(new LeverCMD());
			getServer().getPluginManager().registerEvents(new LeverEvents(), this);
		}
		if (Config.getConfig().tick_counter_enabled) {
			getCommand("tickcounter").setExecutor(new TickCounterCMD());
			getServer().getPluginManager().registerEvents(new TickCounterEvents(), this);
		}
		if(Config.getConfig().build_helper_enabled) {
			getCommand("buildhelper").setExecutor(new BuildHelperCMD());
			getServer().getPluginManager().registerEvents(new BuildHelperEvents(), this);
		}
	}

	public static Main getInstance() {
		return main;
	}

}
