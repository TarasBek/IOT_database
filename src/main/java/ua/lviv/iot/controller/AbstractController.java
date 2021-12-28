package ua.lviv.iot.controller;

import java.io.Serializable;
import java.util.*;

public interface AbstractController<T, K extends Serializable> {
    List<T> findAll();

    T findById(K id);

    T create(T entity);

    T update(K id, T entity);

    void delete(T entity);
}
