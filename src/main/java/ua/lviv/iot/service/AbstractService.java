package ua.lviv.iot.service;

import java.util.*;

public interface AbstractService<T, K> {
    List<T> findAll();

    T findById(K id);

    T create(T entity);

    T update(K id, T entity);

    T delete(K id);
}
