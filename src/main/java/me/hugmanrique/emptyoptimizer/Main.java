package me.hugmanrique.emptyoptimizer;

import me.hugmanrique.emptyoptimizer.manager.TickChanger;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Hugmanrique
 * @since 01/01/2017
 */
public class Main extends JavaPlugin {
    private TickChanger changer;

    private double emptyTps;
    private boolean unloadSpawns;

    private boolean empty = false;

    @Override
    public void onEnable() {
        boolean startup = loadConfig();
        new PlayerListener(this);

        // Init bytecode replacement
        changer = new TickChanger(this);

        if (startup) {
            setEmpty(true);
        }

        if (unloadSpawns) {
            setUnloadSpawns();
        }
    }

    public void setEmpty(boolean empty) {
        if (this.empty == empty) {
            return;
        }

        this.empty = empty;

        if (empty) {
            changer.setTps(emptyTps);
        } else {
            changer.cancel();
        }
    }

    private void setUnloadSpawns() {
        getServer().getWorlds().forEach(world -> world.setKeepSpawnInMemory(false));
    }

    private boolean loadConfig() {
        saveDefaultConfig();

        emptyTps = getConfig().getDouble("emptyTps", 1);
        unloadSpawns = getConfig().getBoolean("unloadSpawnchunks", true);

        return getConfig().getBoolean("startup", true);
    }

    public void registerListener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }
}
