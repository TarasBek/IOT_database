package ua.lviv.iot.dao.impl;

import ua.lviv.iot.annotations.Table;
import ua.lviv.iot.dao.AbstractDao;
import ua.lviv.iot.manager.EntityManager;
import ua.lviv.iot.persistant.ConnectionManager;
import ua.lviv.iot.transformer.Mapper;

import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public abstract class AbstractDaoImpl<T, K> implements AbstractDao<T, K> {

    private final Class<T> clazz;
    private final Mapper<T, K> mapper;
    private final EntityManager<T, K> entityManager;

    private static final Connection CONNECTION = ConnectionManager.getConnection();
    private final String tableName;
    private final String primaryKeyName;

    protected AbstractDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
        this.mapper = new Mapper<>(clazz);
        this.entityManager = new EntityManager<>(clazz);
        this.tableName = this.entityManager.getTableName();
        this.primaryKeyName = this.entityManager.getPrimaryKeyName();
    }

    @Override
    public List<T> findAll() {
        List<T> entities = new LinkedList<>();

        if (clazz.isAnnotationPresent(Table.class)) {
            String sql = String.format("SELECT * FROM `%s`", tableName);
            try (PreparedStatement ps = CONNECTION.prepareStatement(sql)) {
                try (ResultSet resultSet = ps.executeQuery()) {
                    while (resultSet.next()) {
                        addEntity(entities, resultSet);
                    }
                }
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
            }
        }
        return entities;
    }

    @Override
    public T findById(K id) {
        List<T> entities = findByField(primaryKeyName, id);
        return !entities.isEmpty() ? entities.get(0) : null;
    }

    @Override
    public int create(T entity) {
        if (clazz.isAnnotationPresent(Table.class)) {
            String columnsNamesString = entityManager.generateColumnsNamesString();
            String columnsParametersString = entityManager.generateColumnsParametersString();
            String sql = String.format("INSERT INTO `%s` (%s) VALUES (%s)", tableName, columnsNamesString,
                    columnsParametersString);
            System.out.println(sql);
            System.out.println(entity);
            try (PreparedStatement ps = CONNECTION.prepareStatement(sql)) {
                mapper.fillColumnsInPreparedStatement(1, ps, entity);
                return ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
            }
        }
        return 0;
    }

    @Override
    public int update(T entity) {
        if (clazz.isAnnotationPresent(Table.class)) {
            try {
                String updateColumnsString = entityManager.generateUpdateColumnsString();
                K primaryKeyValue = entityManager.getPrimaryKeyValue(entity);
                String sql = String.format("UPDATE `%s` SET %s WHERE %s = ?", tableName, updateColumnsString,
                        primaryKeyName);

                try (PreparedStatement ps = CONNECTION.prepareStatement(sql)) {
                    int nextFreeIndex = mapper.fillColumnsInPreparedStatement(1, ps, entity);
                    boolean isValueSet = mapper.setPreparedStatementByType(nextFreeIndex, ps, primaryKeyValue);
                    if (!isValueSet) {
                        throw new IllegalStateException("Failed to set value. Wrong fieldValue type");
                    }
                    return ps.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("SQLException: " + e.getMessage());
                }
            } catch (IllegalAccessException e) {
                System.out.println("Exception " + e.getClass() + " was thrown. Try again.");
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int delete(K id) {
        if (clazz.isAnnotationPresent(Table.class)) {
            String sql = String.format("DELETE FROM `%s` WHERE %s = ?", tableName, primaryKeyName);

            try (PreparedStatement ps = CONNECTION.prepareStatement(sql)) {
                boolean isValueSet = mapper.setPreparedStatementByType(1, ps, id);
                if (!isValueSet) {
                    throw new IllegalStateException("Failed to set value. Wrong fieldValue type");
                }
                return ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
            }
        }
        return 0;
    }

    private List<T> findByField(String fieldName, Object fieldValue) {
        List<T> entities = new LinkedList<>();

        if (clazz.isAnnotationPresent(Table.class)) {
            String sql = String.format("SELECT * FROM `%s` WHERE %s = ?", tableName, fieldName);
            try (PreparedStatement ps = CONNECTION.prepareStatement(sql)) {
                boolean isValueSet = mapper.setPreparedStatementByType(1, ps, fieldValue);
                if (!isValueSet) {
                    throw new IllegalStateException("Failed to set value. Wrong fieldValue type");
                }
                try (ResultSet resultSet = ps.executeQuery()) {
                    while (resultSet.next()) {
                        addEntity(entities, resultSet);
                    }
                }
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
            }
        }
        return entities;
    }

    private void addEntity(List<T> entities, ResultSet resultSet) {
        try {
            entities.add(mapper.resultSetToEntity(resultSet));
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            System.out.println("Exception " + e.getClass() + " was thrown. Try again.");
        }
    }
}
