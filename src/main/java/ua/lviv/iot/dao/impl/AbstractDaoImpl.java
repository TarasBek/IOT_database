package ua.lviv.iot.dao.impl;

import org.hibernate.*;
import org.hibernate.query.Query;
import ua.lviv.iot.dao.AbstractDao;
import ua.lviv.iot.hibernate.HibernateUtil;

import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.util.*;

public abstract class AbstractDaoImpl<T, K extends Serializable> implements AbstractDao<T, K> {

    private final Class<T> clazz;
    private final SessionFactory sessionFactory;

    protected AbstractDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public List<T> findAll() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Query<T> query = session.createQuery("from " + clazz.getName(), clazz);
            List<T> resultList = query.getResultList();
            transaction.commit();
            return resultList;
        }
    }

    @Override
    public T findById(K id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            T entity;
            try {
                entity = session.get(clazz, id);
            } catch (TypeMismatchException e) {
                try {
                    entity = session.get(clazz, Integer.parseInt((String) id));
                } catch (ClassCastException | NumberFormatException ex) {
                    return null;
                }
            } catch (IllegalArgumentException e) {
                return null;
            }
            transaction.commit();
            return entity;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public K create(T entity) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Object id;
            try {
                id = session.save(entity);
            } catch (PropertyValueException e) {
                return null;
            }
            transaction.commit();
            return (K) id;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T update(T entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Object updated = session.merge(entity);
            transaction.commit();
            return (T) updated;
        } catch (PersistenceException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return null;
        }
    }

    @Override
    public void delete(T entity) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        }
    }
}
