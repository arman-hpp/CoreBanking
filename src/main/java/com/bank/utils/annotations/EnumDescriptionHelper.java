package com.bank.utils.annotations;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class EnumDescriptionHelper {
    public static String GetEnumDescription(Enum<?> e) {
        try {
            Class<?> clazz = e.getClass();
            Field field = clazz.getField(e.toString());
            field.setAccessible(true);
            if (field.isAnnotationPresent(EnumDescription.class)) {
                return field.getAnnotation(EnumDescription.class).value();
            } else {
                return "";
            }
        } catch (Exception ex) {
            return "";
        }
    }

    public static Map<Integer, String> GetEnumDescriptions(Class<?> clazz) {
        Map<Integer, String> enumElementsMap = new HashMap<>();
        try {
            for (Field field : clazz.getFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(EnumDescription.class)) {
                    enumElementsMap.put(
                            ((Enum<?>) field.get(null)).ordinal(),
                            field.getAnnotation(EnumDescription.class).value()
                    );
                }
            }

            return enumElementsMap;
        } catch (Exception ex) {
            return new HashMap<>();
        }
    }
}
