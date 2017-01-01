package me.hugmanrique.emptyoptimizer;

import me.hugmanrique.emptyoptimizer.manager.TickTransformer;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Hugmanrique
 * @since 01/01/2017
 */
public class Main extends JavaPlugin {
    private boolean empty = true;

    private TickTransformer transformer;

    @Override
    public void onEnable() {
        transformer = new TickTransformer(this);
        new PlayerListener(this);
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;

        if (empty) {

        }
    }

    public void registerListener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }
}
