package ua.lviv.iot.service;

import java.io.Serializable;
import java.util.*;

public interface AbstractService<T, K extends Serializable> {
    List<T> findAll();

    T findById(K id);

    T create(T entity);

    T update(K id, T entity);

    void delete(T entity);
}
