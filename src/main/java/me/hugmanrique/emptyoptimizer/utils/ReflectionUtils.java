package me.hugmanrique.emptyoptimizer.utils;

import org.bukkit.Bukkit;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Reflection Utils which you can use to make methods using reflection easier, faster and more efficient
 * @see <a href="https://gist.github.com/hugmanrique/4a330dd2d06eec126b452e614c4fb88d">Gist</a>
 * @author Hugmanrique
 * @since 17/11/2016
 */
public class ReflectionUtils {
    public static Class<?> getClass(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Field getField(Class<?> clazz, String fieldName) {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setField(Field field, Object object, Object value) {
        try {
            field.setAccessible(true);
            field.set(object, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    // Custom added
    private static final String name = Bukkit.getServer().getClass().getPackage().getName();
    private static final String version = name.substring(name.lastIndexOf('.') + 1);

    public static Class<?> getNMSClass(String className) {
        return getClass("net.minecraft.server." + version + '.' + className);
    }
}
