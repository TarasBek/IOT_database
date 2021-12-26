package ua.lviv.iot.manager;

import lombok.Getter;
import ua.lviv.iot.annotations.Column;
import ua.lviv.iot.annotations.PrimaryKey;
import ua.lviv.iot.annotations.Table;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.*;

@Getter
public class EntityManager<T, K> {

    private final Class<T> clazz;
    private final Field[] fields;

    public EntityManager(Class<T> clazz) {
        this.clazz = clazz;
        this.fields = clazz.getDeclaredFields();
    }

    public String getTableName() {
        Table table = clazz.getAnnotation(Table.class);
        return table.name();
    }

    public String getPrimaryKeyName() {
        return Arrays.stream(fields)
                .filter(f -> f.isAnnotationPresent(PrimaryKey.class))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Class doesn't have PrimaryKey"))
                .getAnnotation(Column.class)
                .name();
    }

    @SuppressWarnings("unchecked")
    public K getPrimaryKeyValue(T entity) throws IllegalAccessException {
        Field field = Arrays.stream(fields)
                .filter(f -> f.isAnnotationPresent(PrimaryKey.class))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Class doesn't have PrimaryKey"));
        field.setAccessible(true);
        return (K) field.get(entity);
    }

    public List<String> getColumnsNames() {
        return getColumns().stream()
                .map(col -> col.isAnnotationPresent(Column.class) ? col.getAnnotation(Column.class).name() : col.getName())
                .collect(Collectors.toList());
    }

    public List<String> getFieldsNames() {
        return Arrays.stream(fields)
                .map(f -> f.isAnnotationPresent(Column.class) ? f.getAnnotation(Column.class).name() : f.getName())
                .collect(Collectors.toList());
    }

    public String generateColumnsNamesString() {
        return String.join(", ", getColumnsNames());
    }

    public String generateColumnsParametersString() {
        List<String> columns = getColumnsNames();
        columns.replaceAll(s -> s = "?");
        return String.join(", ", columns);
    }

    public String generateUpdateColumnsString() {
        List<String> columns = getColumnsNames();
        columns.replaceAll(s -> s += " = ?");
        return String.join(", ", columns);
    }

    public List<Field> getColumns() {
        return Arrays.stream(fields)
                .filter(f -> !f.isAnnotationPresent(PrimaryKey.class))
                .collect(Collectors.toList());
    }

    public List<String> getFieldsValues(T entity) {
        return Arrays.stream(fields)
                .map(f -> {
                    f.setAccessible(true);
                    try {
                        return f.get(entity) == null ? "" : f.get(entity).toString();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return "null";
                })
                .collect(Collectors.toList());
    }

    public Object[] getFieldsValuesArr(T entity) {
        return Arrays.stream(fields)
                .map(f -> {
                    f.setAccessible(true);
                    try {
                        return f.get(entity) == null ? "" : f.get(entity);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return "null";
                })
                .toArray();
    }

    public String[] getFieldsNamesArr() {
        return Arrays.stream(fields)
                .map(f -> f.isAnnotationPresent(Column.class) ? f.getAnnotation(Column.class).name() : f.getName())
                .toArray(String[]::new);
    }
}
