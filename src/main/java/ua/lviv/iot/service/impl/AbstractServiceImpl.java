package ua.lviv.iot.service.impl;

import ua.lviv.iot.dao.AbstractDao;
import ua.lviv.iot.service.AbstractService;

import java.io.Serializable;
import java.util.*;

public abstract class AbstractServiceImpl<T, K extends Serializable> implements AbstractService<T, K> {

    private final AbstractDao<T, K> abstractDao;

    protected AbstractServiceImpl(AbstractDao<T, K> abstractDao) {
        this.abstractDao = abstractDao;
    }

    @Override
    public List<T> findAll() {
        return abstractDao.findAll();
    }

    @Override
    public T findById(K id) {
        return abstractDao.findById(id);
    }

    @Override
    public T create(T entity) {
        K id = abstractDao.create(entity);
        T newEntity = null;
        if (!(Objects.equals(id, "") || Objects.equals(id, 0))) {
            newEntity = findById(id);
        }
        return newEntity;
    }

    @Override
    public T update(K id, T entity) {
        T oldEntity = findById(id);
        T updated = abstractDao.update(entity);
        if (updated != null) {
            return oldEntity;
        }
        return null;
    }

    @Override
    public void delete(T entity) {
        abstractDao.delete(entity);
    }
}
