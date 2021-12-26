package ua.lviv.iot.dao;

import java.util.*;

public interface AbstractDao<T, K> {
    List<T> findAll();

    T findById(K id);

    int create(T entity);

    int update(T entity);

    int delete(K id);
}
