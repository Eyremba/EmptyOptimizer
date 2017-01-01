package me.hugmanrique.emptyoptimizer;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author Hugmanrique
 * @since 01/01/2017
 */
public class PlayerListener implements Listener {
    private Main main;

    public PlayerListener(Main main) {
        this.main = main;
        main.registerListener(this);
    }

    @EventHandler
    public void onLogin(AsyncPlayerPreLoginEvent e) {
        if (!e.getLoginResult().equals(AsyncPlayerPreLoginEvent.Result.ALLOWED)) {
            return;
        }

        main.setEmpty(false);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (Bukkit.getOnlinePlayers().size() > 0) {
            return;
        }

        main.setEmpty(true);
    }
}
