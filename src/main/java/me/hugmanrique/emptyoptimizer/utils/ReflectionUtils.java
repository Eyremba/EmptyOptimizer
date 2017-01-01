package me.hugmanrique.emptyoptimizer.utils;

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

    public static Object getObject(Field field, Object object) {
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getInt(Field field, Object object) {
        try {
            return field.getInt(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static double getDouble(Field field, Object object) {
        try {
            return field.getDouble(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return 0D;
        }
    }

    public static float getFloat(Field field, Object object) {
        try {
            return field.getFloat(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Method getMethod(Class<?> clazz, String name) {
        try {
            return clazz.getMethod(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
