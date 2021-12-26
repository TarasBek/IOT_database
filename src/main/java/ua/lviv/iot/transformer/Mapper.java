package ua.lviv.iot.transformer;

import ua.lviv.iot.annotations.Column;
import ua.lviv.iot.manager.EntityManager;

import java.lang.reflect.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

public class Mapper<T, K> {

    private final EntityManager<T, K> entityManager;

    public Mapper(Class<T> clazz) {
        this.entityManager = new EntityManager<>(clazz);
    }

    public T resultSetToEntity(ResultSet resultSet) throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        T entity = entityManager.getClazz().getConstructor().newInstance();
        Arrays.stream(entityManager.getFields())
                .forEach(f -> {
                    String name = f.isAnnotationPresent(Column.class) ? f.getAnnotation(Column.class).name() : f.getName();
                    Class<?> fType = f.getType();
                    f.setAccessible(true); // prevent IllegalAccessException to f
                    try {
                        if (fType == String.class) {
                            f.set(entity, resultSet.getString(name));
                        } else if (fType == Integer.class) {
                            f.set(entity, resultSet.getInt(name));
                        } else if (fType == LocalDateTime.class) {
                            f.set(entity, resultSet.getTimestamp(name).toLocalDateTime());
                        }
                    } catch (SQLException e) {
                        System.out.println("SQLException: " + e.getMessage());
                    } catch (IllegalAccessException e) {
                        System.out.println("IllegalAccessException: " + e.getMessage());
                    }
                });
        return entity;
    }

    public boolean setPreparedStatementByType(int index, PreparedStatement ps, Object value) throws SQLException {
        Class<?> fieldType = value.getClass();
        if (fieldType == Integer.class) {
            ps.setInt(index, (Integer) value);
        } else if (fieldType == String.class) {
            ps.setString(index, (String) value);
        } else if (fieldType == LocalDateTime.class) {
            ps.setTimestamp(index, Timestamp.valueOf((LocalDateTime) value));
        } else if (fieldType == Boolean.class) {
            ps.setBoolean(index, (boolean) value);
        } else {
            return false;
        }
        return true;
    }

    public int fillColumnsInPreparedStatement(int startFromIndex, PreparedStatement ps, T entity) throws SQLException {
        int index = startFromIndex;
        for (Field field : entityManager.getColumns()) {
            try {
                field.setAccessible(true);
                setPreparedStatementByType(index, ps, field.get(entity));
                index++;
            } catch (IllegalAccessException e) {
                System.out.println("Exception " + e.getClass() + " was thrown. Try again.");
                e.printStackTrace();
            }
        }
        int nextFreeIndex = index;
        return nextFreeIndex;
    }
}
