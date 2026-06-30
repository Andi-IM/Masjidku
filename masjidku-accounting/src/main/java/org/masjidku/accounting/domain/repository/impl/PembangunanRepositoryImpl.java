package org.masjidku.accounting.domain.repository.impl;

import org.masjidku.accounting.domain.entity.PembangunanEntity;
import org.masjidku.accounting.domain.repository.PembangunanRepository;
import org.masjidku.accounting.domain.repository.base.HibernateUtil;
import org.masjidku.accounting.domain.repository.exception.DataAccessException;
import java.util.List;
import java.util.Optional;

public class PembangunanRepositoryImpl implements PembangunanRepository {

    @Override
    public Optional<PembangunanEntity> findById(String id) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            return Optional.ofNullable(session.get(PembangunanEntity.class, id));
        } catch (Exception e) {
            throw new DataAccessException("Failed to get PembangunanEntity by ID", e);
        }
    }

    @Override
    public List<PembangunanEntity> findAll() {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            return session.createQuery("FROM PembangunanEntity", PembangunanEntity.class).list();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get all PembangunanEntity", e);
        }
    }

    @Override
    public void save(PembangunanEntity entity) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.persist(entity);
        } catch (Exception e) {
            throw new DataAccessException("Failed to save PembangunanEntity", e);
        }
    }

    @Override
    public void update(PembangunanEntity entity) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.merge(entity);
        } catch (Exception e) {
            throw new DataAccessException("Failed to update PembangunanEntity", e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            PembangunanEntity entity = session.get(PembangunanEntity.class, id);
            if (entity != null) {
                session.remove(entity);
            }
        } catch (Exception e) {
            throw new DataAccessException("Failed to delete PembangunanEntity", e);
        }
    }

    @Override
    public boolean exists(String id) {
        return findById(id).isPresent();
    }

    @Override
    public PembangunanEntity getLastRecord() {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            var query = session.createQuery("FROM PembangunanEntity ORDER BY id DESC", PembangunanEntity.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get last PembangunanEntity", e);
        }
    }

    @Override
    public String getTotal() {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            var count = session.createQuery("SELECT IFNULL(SUM(CAST(e.jumlah AS double)), 0) FROM PembangunanEntity e", Double.class).uniqueResult();
            return count != null ? String.valueOf(count.longValue()) : "0";
        } catch (Exception e) {
            throw new DataAccessException("Failed to get total PembangunanEntity", e);
        }
    }
}
