package me.hugmanrique.emptyoptimizer;

import me.hugmanrique.emptyoptimizer.manager.TickChanger;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Hugmanrique
 * @since 01/01/2017
 */
public class Main extends JavaPlugin {
    private static final int SEC_IN_NANOS = 1000000000;
    public static int RUN_TIME = SEC_IN_NANOS / 20;

    private TickChanger changer;

    private int normalTps;
    private int emptyTps;
    private boolean unloadSpawns;

    private boolean empty = true;

    @Override
    public void onEnable() {
        loadConfig();
        new PlayerListener(this);

        // Init bytecode replacement
        changer = new TickChanger(this);

        if (unloadSpawns) {
            setUnloadSpawns();
        }
    }

    public void setEmpty(boolean empty) {
        if (this.empty == empty) {
            return;
        }

        this.empty = empty;
        RUN_TIME = SEC_IN_NANOS / (empty ? emptyTps : normalTps);
    }

    private void setUnloadSpawns() {
        getServer().getWorlds().forEach(world -> world.setKeepSpawnInMemory(false));
    }

    private void loadConfig() {
        saveDefaultConfig();

        normalTps = getConfig().getInt("normalTps", 20);
        emptyTps = getConfig().getInt("emptyTps", 1);
        unloadSpawns = getConfig().getBoolean("unloadSpawnchunks", true);

        RUN_TIME = SEC_IN_NANOS / emptyTps;
    }

    public void registerListener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }
}
