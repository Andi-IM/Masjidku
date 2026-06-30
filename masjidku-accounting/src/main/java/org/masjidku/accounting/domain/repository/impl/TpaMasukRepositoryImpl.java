package org.masjidku.accounting.domain.repository.impl;

import org.masjidku.accounting.domain.entity.TpaMasukEntity;
import org.masjidku.accounting.domain.repository.TpaMasukRepository;
import org.masjidku.accounting.domain.repository.base.HibernateUtil;
import org.masjidku.accounting.domain.repository.exception.DataAccessException;
import java.util.List;
import java.util.Optional;

public class TpaMasukRepositoryImpl implements TpaMasukRepository {

    @Override
    public Optional<TpaMasukEntity> findById(String id) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            return Optional.ofNullable(session.get(TpaMasukEntity.class, id));
        } catch (Exception e) {
            throw new DataAccessException("Failed to get TpaMasukEntity by ID", e);
        }
    }

    @Override
    public List<TpaMasukEntity> findAll() {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            return session.createQuery("FROM TpaMasukEntity", TpaMasukEntity.class).list();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get all TpaMasukEntity", e);
        }
    }

    @Override
    public void save(TpaMasukEntity entity) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.persist(entity);
        } catch (Exception e) {
            throw new DataAccessException("Failed to save TpaMasukEntity", e);
        }
    }

    @Override
    public void update(TpaMasukEntity entity) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.merge(entity);
        } catch (Exception e) {
            throw new DataAccessException("Failed to update TpaMasukEntity", e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            TpaMasukEntity entity = session.get(TpaMasukEntity.class, id);
            if (entity != null) {
                session.remove(entity);
            }
        } catch (Exception e) {
            throw new DataAccessException("Failed to delete TpaMasukEntity", e);
        }
    }

    @Override
    public boolean exists(String id) {
        return findById(id).isPresent();
    }

    @Override
    public TpaMasukEntity getLastRecord() {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            var query = session.createQuery("FROM TpaMasukEntity ORDER BY id DESC", TpaMasukEntity.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get last TpaMasukEntity", e);
        }
    }

    @Override
    public String getTotal() {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            var count = session.createQuery("SELECT IFNULL(SUM(CAST(e.jumlah AS double)), 0) FROM TpaMasukEntity e", Double.class).uniqueResult();
            return count != null ? String.valueOf(count.longValue()) : "0";
        } catch (Exception e) {
            throw new DataAccessException("Failed to get total TpaMasukEntity", e);
        }
    }
}
