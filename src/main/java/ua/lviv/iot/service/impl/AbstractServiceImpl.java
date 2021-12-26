package ua.lviv.iot.service.impl;

import ua.lviv.iot.dao.AbstractDao;
import ua.lviv.iot.service.AbstractService;

import java.util.*;

public abstract class AbstractServiceImpl<T, K> implements AbstractService<T, K> {

    private AbstractDao<T, K> abstractDao;

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
        int countOfCreated = abstractDao.create(entity);
        T newEntity = null;
        if (countOfCreated == 1) {
            List<T> entities = findAll();
            newEntity = entities.get(entities.size() - 1);

        }
        return newEntity;
    }

    @Override
    public T update(K id, T entity) {
        T beforeUpdate = findById(id);
        int countOfUpdated = abstractDao.update(entity);
        if (countOfUpdated == 1) {
            return beforeUpdate;
        }
        return null;
    }

    @Override
    public T delete(K id) {
        T beforeUpdate = findById(id);
        int countOfUpdated = abstractDao.delete(id);
        if (countOfUpdated == 1) {
            return beforeUpdate;
        }
        return null;
    }
}
