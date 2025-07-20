package org.example.task4;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class JsonSerializer {
    public static String toJson(Object obj) {
        Class<?> clazz = obj.getClass();
        StringBuilder json = new StringBuilder();
        json.append("{");

        List<String> jsonFields = new ArrayList<>();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(JsonField.class)) {
                field.setAccessible(true);
                JsonField annotation = field.getAnnotation(JsonField.class);
                try {
                    Object value = field.get(obj);
                    String jsonEntry = "\"" + annotation.name() + "\": " + formatValue(value);
                    jsonFields.add(jsonEntry);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Не удалось сериализовать поле: " + field.getName(), e);
                }
            }
        }

        json.append(String.join(", ", jsonFields));
        json.append("}");
        return json.toString();
    }

    private static String formatValue(Object value) {
        if (value instanceof String) {
            return "\"" + value + "\"";
        } else {
            return String.valueOf(value);
        }
    }
}
