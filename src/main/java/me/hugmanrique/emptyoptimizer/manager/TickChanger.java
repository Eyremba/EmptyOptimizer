package me.hugmanrique.emptyoptimizer.manager;

import me.hugmanrique.emptyoptimizer.Main;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Hugmanrique
 * @since 01/01/2017
 */
public class TickChanger {
    private static final int TICK_TIME = 1000 / 20;

    private Main main;
    private BukkitRunnable runnable;

    public TickChanger(Main main) {
        this.main = main;
    }

    public void setTps(double tps) {
        cancel();

        if (tps >= 20D) {
            return;
        }

        final long sleep = getSleepMillis(tps);

        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        runnable.runTaskTimer(main, 1L, 1L);
    }

    public void cancel() {
        if (runnable == null) {
            return;
        }

        runnable.cancel();
    }

    private long getSleepMillis(double tps) {
        return 1000 - (long) (TICK_TIME * tps);
    }
}
