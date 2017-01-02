package me.hugmanrique.emptyoptimizer;

import me.hugmanrique.emptyoptimizer.manager.TickChanger;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

/**
 * @author Hugmanrique
 * @since 01/01/2017
 */
public class Main extends JavaPlugin {
    private boolean empty = false;

    private int normalTps;
    private int emptyTps;

    @Override
    public void onEnable() {
        loadConfig();
        new PlayerListener(this);

        setEmpty(true);
    }

    public void setEmpty(boolean empty) {
        if (this.empty == empty) {
            return;
        }

        this.empty = empty;
        TickChanger.setTps(empty ? emptyTps : normalTps);
    }

    private void loadConfig() {
        saveDefaultConfig();

        normalTps = getConfig().getInt("normalTps", 20);
        emptyTps = getConfig().getInt("emptyTps", 1);
    }

    public void registerListener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }
}
