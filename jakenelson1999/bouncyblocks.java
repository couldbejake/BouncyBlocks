package jakenelson1999;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConfigurationOptions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

public class Main extends JavaPlugin implements Listener {
	
    public void onEnable() {
		
		// Creates config folder
        getDataFolder().mkdir();
        getLogger().info("Mining Anti Cheat By JakeNelson1999 has been loaded!");
		
		// Creates plugin manager and begins registering all events.
        PluginManager manager = Bukkit.getServer().getPluginManager();
        manager.registerEvents(this, this);

		// Loads the config file link.
        File ConfigFile = new File(getDataFolder(), "config.yml");
        final YamlConfiguration config = YamlConfiguration.loadConfiguration(ConfigFile);

		// If the config file does not exist.
        if (!ConfigFile.exists()) {
			/*
				Create a new config file with correct values.
			*/
			
            config.options().header("If Scheduled is set to false, bouncing will only work when the player moves. Note: Setting to false creates less lag! #Set The value below to either True, False or Both");
            config.set("Scheduled Detection?", "False");
            config.set("Schedule Delay in ticks", Integer.valueOf(20));

            config.set("Small Bounce Height", Integer.valueOf(1));
            config.set("Large Bounce Height", Integer.valueOf(32));

            List < String > list = new ArrayList();
            list.add("4");
            list.add("20");
            config.set("Small Bounce Blocks", list);

            List < String > list2 = new ArrayList();
            list2.add("19");
            list2.add("29");
            config.set("Large Bounce Blocks", list2);
            try {
                config.save(ConfigFile);
            } catch (IOException localIOException) {}
        }


        if ((((String) config.get("Scheduled Detection?")).toLowerCase().contains("true")) || (((String) config.get("Scheduled Detection?")).toLowerCase().contains("both"))) {
            final List < String > small_bounce = config.getStringList("Small Bounce Blocks");
            final List < String > large_bounce = config.getStringList("Large Bounce Blocks");

			// Create a scheduler for the timing based bouncing.
            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
                public void run() {
                    for (Player pla: Bukkit.getServer().getOnlinePlayers()) {
                        if (small_bounce.contains(pla.getLocation().getBlock().getRelative(BlockFace.DOWN).getTypeId())) {
                            pla.setVelocity(new Vector(0, ((Integer) config.get("Small Bounce Height")).intValue(), 0));
                        }
                        if (large_bounce.contains(pla.getLocation().getBlock().getRelative(BlockFace.DOWN).getTypeId())) {
                            pla.setVelocity(new Vector(0, ((Integer) config.get("Large Bounce Height")).intValue(), 0));
                        }

                    }

                }
            }, 0 L, 20 L);
        }
    }


	// If a player joins
    @EventHandler
    public void plaJoin(PlayerMoveEvent event) {
		/*
			Check whether the player is allowed to bounce and apply correct velocities.
		*/
		
        File ConfigFile = new File(getDataFolder(), "config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(ConfigFile);

        List < String > small_bounce = config.getStringList("Small Bounce Blocks");
        List < String > large_bounce = config.getStringList("Large Bounce Blocks");

        if ((((String) config.get("Scheduled Detection?")).toLowerCase().contains("false")) || (((String) config.get("Scheduled Detection?")).toLowerCase().contains("both"))) {
            if (small_bounce.contains(event.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getTypeId())) {
				// Set player velocity on small bounce.
                event.getPlayer().setVelocity(new Vector(0, ((Integer) config.get("Small Bounce Height")).intValue(), 0));
            }
            if (large_bounce.contains(event.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getTypeId())) {
				// Set player velocity on large bounce.
                event.getPlayer().setVelocity(new Vector(0, ((Integer) config.get("Large Bounce Height")).intValue(), 0));
            }
        }
    }
}
