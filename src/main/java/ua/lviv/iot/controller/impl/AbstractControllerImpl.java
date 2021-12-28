package ua.lviv.iot.controller.impl;

import ua.lviv.iot.controller.AbstractController;
import ua.lviv.iot.service.AbstractService;

import java.util.*;

public abstract class AbstractControllerImpl<T, K> implements AbstractController<T, K> {

    private AbstractService<T, K> abstractService;

    protected AbstractControllerImpl(AbstractService<T, K> abstractService) {
        this.abstractService = abstractService;
    }

    @Override
    public List<T> findAll() {
        return abstractService.findAll();
    }

    @Override
    public T findById(K id) {
        return abstractService.findById(id);
    }

    @Override
    public T create(T entity) {
        return abstractService.create(entity);
    }

    @Override
    public T update(K id, T entity) {
        return abstractService.update(id, entity);
    }

    @Override
    public T delete(K id) {
        return abstractService.delete(id);
    }
}
