package org.masjidku.events.domain.repository.impl;

import org.hibernate.SessionFactory;
import org.masjidku.events.domain.repository.exception.DataAccessException;

import java.util.List;
import java.util.Optional;

public abstract class BaseEventRepositoryImpl<T> {

    protected final SessionFactory sessionFactory;
    protected final Class<T> entityClass;
    protected final String idFieldName;
    protected final String namaFieldName;

    protected BaseEventRepositoryImpl(SessionFactory sessionFactory, Class<T> entityClass, String idFieldName, String namaFieldName) {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
        this.idFieldName = idFieldName;
        this.namaFieldName = namaFieldName;
    }

    protected Optional<T> findById(String id) {
        try {
            var session = sessionFactory.getCurrentSession();
            return Optional.ofNullable(session.get(entityClass, id));
        } catch (Exception e) {
            throw new DataAccessException("Failed to get " + entityClass.getSimpleName() + " by ID", e);
        }
    }

    protected List<T> findAll() {
        try {
            var session = sessionFactory.getCurrentSession();
            var builder = session.getCriteriaBuilder();
            var query = builder.createQuery(entityClass);
            var root = query.from(entityClass);
            query.select(root);
            return session.createQuery(query).list();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get all " + entityClass.getSimpleName(), e);
        }
    }

    protected void persist(T entity) {
        try {
            var session = sessionFactory.getCurrentSession();
            session.persist(entity);
        } catch (Exception e) {
            throw new DataAccessException("Failed to save " + entityClass.getSimpleName(), e);
        }
    }

    protected void merge(T entity) {
        try {
            var session = sessionFactory.getCurrentSession();
            session.merge(entity);
        } catch (Exception e) {
            throw new DataAccessException("Failed to update " + entityClass.getSimpleName(), e);
        }
    }

    protected void remove(String id) {
        try {
            var session = sessionFactory.getCurrentSession();
            T entity = session.get(entityClass, id);
            if (entity != null) {
                session.remove(entity);
            }
        } catch (Exception e) {
            throw new DataAccessException("Failed to delete " + entityClass.getSimpleName(), e);
        }
    }

    protected boolean checkExists(String id) {
        try {
            var session = sessionFactory.getCurrentSession();
            var builder = session.getCriteriaBuilder();
            var query = builder.createQuery(Long.class);
            var root = query.from(entityClass);
            query.select(builder.count(root)).where(builder.equal(root.get(idFieldName), id));
            var count = session.createQuery(query).uniqueResult();
            return count != null && count > 0;
        } catch (Exception e) {
            throw new DataAccessException("Failed to check if " + entityClass.getSimpleName() + " exists", e);
        }
    }

    protected List<String> findAllNames() {
        try {
            var session = sessionFactory.getCurrentSession();
            var builder = session.getCriteriaBuilder();
            var query = builder.createQuery(String.class);
            var root = query.from(entityClass);
            query.select(root.get(namaFieldName));
            return session.createQuery(query).list();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get all " + entityClass.getSimpleName() + " names", e);
        }
    }

    protected String findIdByName(String name) {
        try {
            var session = sessionFactory.getCurrentSession();
            var builder = session.getCriteriaBuilder();
            var query = builder.createQuery(String.class);
            var root = query.from(entityClass);
            query.select(root.get(idFieldName)).where(builder.equal(root.get(namaFieldName), name));
            var result = session.createQuery(query).uniqueResult();
            return result != null ? result : "";
        } catch (Exception e) {
            throw new DataAccessException("Failed to get " + entityClass.getSimpleName() + " ID by name", e);
        }
    }
}
