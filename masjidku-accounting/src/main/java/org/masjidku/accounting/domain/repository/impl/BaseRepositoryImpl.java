package org.masjidku.accounting.domain.repository.impl;

import org.hibernate.SessionFactory;
import org.masjidku.accounting.domain.repository.BaseRepository;
import org.masjidku.accounting.domain.repository.exception.DataAccessException;

import java.util.List;
import java.util.Optional;

public abstract class BaseRepositoryImpl<T> implements BaseRepository<T> {

    protected final SessionFactory sessionFactory;
    protected final Class<T> entityClass;

    protected BaseRepositoryImpl(SessionFactory sessionFactory, Class<T> entityClass) {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
    }

    @Override
    public Optional<T> findById(String id) {
        try {
            var session = sessionFactory.getCurrentSession();
            return Optional.ofNullable(session.get(entityClass, id));
        } catch (Exception e) {
            throw new DataAccessException("Failed to get " + entityClass.getSimpleName() + " by ID", e);
        }
    }

    @Override
    public List<T> findAll() {
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

    @Override
    public void save(T entity) {
        try {
            var session = sessionFactory.getCurrentSession();
            session.persist(entity);
        } catch (Exception e) {
            throw new DataAccessException("Failed to save " + entityClass.getSimpleName(), e);
        }
    }

    @Override
    public void update(T entity) {
        try {
            var session = sessionFactory.getCurrentSession();
            session.merge(entity);
        } catch (Exception e) {
            throw new DataAccessException("Failed to update " + entityClass.getSimpleName(), e);
        }
    }

    @Override
    public void delete(String id) {
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

    @Override
    public boolean exists(String id) {
        return findById(id).isPresent();
    }

    @Override
    public T getLastRecord() {
        try {
            var session = sessionFactory.getCurrentSession();
            var builder = session.getCriteriaBuilder();
            var query = builder.createQuery(entityClass);
            var root = query.from(entityClass);
            query.select(root).orderBy(builder.desc(root.get("id")));
            return session.createQuery(query).setMaxResults(1).uniqueResult();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get last " + entityClass.getSimpleName(), e);
        }
    }

    @Override
    public String getTotal() {
        try {
            var session = sessionFactory.getCurrentSession();
            var builder = session.getCriteriaBuilder();
            var query = builder.createQuery(java.math.BigDecimal.class);
            var root = query.from(entityClass);
            query.select(builder.coalesce(builder.sum(root.get("jumlah")), new java.math.BigDecimal(0)));
            var count = session.createQuery(query).uniqueResult();
            return count != null ? count.toPlainString() : "0";
        } catch (Exception e) {
            throw new DataAccessException("Failed to get total " + entityClass.getSimpleName(), e);
        }
    }
}
