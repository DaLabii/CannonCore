package dalabi.cannon;

import org.bukkit.plugin.java.JavaPlugin;

import dalabi.cannon.fire.FireCMD;
import dalabi.cannon.fire.FireEvents;
import dalabi.cannon.magicsand.MagicsandCMD;
import dalabi.cannon.magicsand.MagicsandEvents;
import dalabi.cannon.magicsand.MagicsandRefillCMD;
import dalabi.cannon.magicsand.MagicsandUtil;

public class Main extends JavaPlugin {
	private static Main main;

	public void onEnable() {
		Main.main = this;
		this.getConfig().options().copyDefaults(true);
		this.saveDefaultConfig();
		Config.reload();
		this.getCommand("cannoncore").setExecutor(new CannonCoreCMD());
		if (Config.magicsand_enabled) {
			this.getCommand("refill").setExecutor(new MagicsandRefillCMD());
			this.getCommand("ms").setExecutor(new MagicsandCMD());
			this.getServer().getPluginManager().registerEvents(new MagicsandEvents(), this);
			MagicsandUtil.startMagicsandTasks();
		}
		if (Config.button_enabled) {
			this.getCommand("fire").setExecutor(new FireCMD());
			this.getServer().getPluginManager().registerEvents(new FireEvents(), this);
		}
	}

	public static Main getInstance() {
		return Main.main;
	}

}
