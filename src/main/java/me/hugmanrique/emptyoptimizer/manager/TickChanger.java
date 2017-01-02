package me.hugmanrique.emptyoptimizer.manager;

import me.hugmanrique.emptyoptimizer.utils.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @author Hugmanrique
 * @since 01/01/2017
 */
public class TickChanger {
    private static final Class<?> SERVER_CLASS;

    private static final Field TPS_FIELD;
    private static final Field TICK_TIME_FIELD;

    static {
        SERVER_CLASS = ReflectionUtils.getNMSClass("MinecraftServer");

        TPS_FIELD = ReflectionUtils.getField(SERVER_CLASS, "TPS");
        TICK_TIME_FIELD = ReflectionUtils.getField(SERVER_CLASS, "TICK_TIME");
    }

    public static void setTps(int tps) {
        ReflectionUtils.setStaticFinal(TPS_FIELD, tps);
        ReflectionUtils.setStaticFinal(TICK_TIME_FIELD, 1000000000 / tps);
    }
}
