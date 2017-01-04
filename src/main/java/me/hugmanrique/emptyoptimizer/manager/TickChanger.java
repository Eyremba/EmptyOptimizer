package me.hugmanrique.emptyoptimizer.manager;

import me.hugmanrique.emptyoptimizer.Main;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Hugmanrique
 * @since 01/01/2017
 */
public class TickChanger {
    private static final int TICK_TIME = 1000 / 20;

    private Main main;
    private final Timer timer;
    private TimerTask task;

    public TickChanger(Main main) {
        this.main = main;
        this.timer = new Timer("TickChanger");
    }

    public void setTps(double tps) {
        cancel();

        if (tps >= 20D) {
            return;
        }

        final long sleep = getSleepMillis(tps);

        task = new TimerTask() {
            @Override
            public void run() {
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    public void cancel() {
        if (task == null) {
            return;
        }

        task.cancel();
    }

    private long getSleepMillis(double tps) {
        return 1000 - (long) (TICK_TIME * tps);
    }
}
