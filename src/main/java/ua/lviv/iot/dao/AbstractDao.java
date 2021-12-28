package ua.lviv.iot.dao;

import java.io.Serializable;
import java.util.*;

public interface AbstractDao<T, K extends Serializable> {
    List<T> findAll();

    T findById(K id);

    K create(T entity);

    T update(T entity);

    void delete(T entity);
}
