package ua.lviv.iot.controller;

import java.util.*;

public interface AbstractController<T, K> {
    List<T> findAll();

    T findById(K id);

    T create(T entity);

    T update(K id, T entity);

    T delete(K id);
}
