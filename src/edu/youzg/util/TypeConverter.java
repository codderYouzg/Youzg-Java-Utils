package edu.youzg.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 类型转换器<br/>
 * 将 所传类型的全路径名 转换为 相应的类型
 * @author Youzg
 */
public class TypeConverter {
    private static final Map<String, Class<?>> primeType = new HashMap<String, Class<?>>(); // 存储 八大基本类型 的 转换原则

    static {
        primeType.put("byte", byte.class);
        primeType.put("char", char.class);
        primeType.put("short", short.class);
        primeType.put("boolean", boolean.class);
        primeType.put("int", int.class);
        primeType.put("long", long.class);
        primeType.put("float", float.class);
        primeType.put("double", double.class);
    }

    public TypeConverter() {
    }

    /**
     * 将 目标类的全路径名 转换成 该类的Class对象
     * @param typeName 目标类的全路径名
     * @return 该类的Class对象
     */
    public static Class<?> toType(String typeName) {
        Class<?> result = primeType.get(typeName);

        if (result == null) {
            try {
                return Class.forName(typeName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
