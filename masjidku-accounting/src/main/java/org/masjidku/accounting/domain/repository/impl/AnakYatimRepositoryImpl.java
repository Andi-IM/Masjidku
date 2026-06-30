package org.masjidku.accounting.domain.repository.impl;

import org.masjidku.accounting.domain.entity.AnakYatimEntity;
import org.masjidku.accounting.domain.repository.AnakYatimRepository;
import org.masjidku.accounting.domain.repository.base.HibernateUtil;
import org.masjidku.accounting.domain.repository.exception.DataAccessException;
import java.util.List;
import java.util.Optional;

public class AnakYatimRepositoryImpl implements AnakYatimRepository {

    @Override
    public Optional<AnakYatimEntity> findById(String id) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            return Optional.ofNullable(session.get(AnakYatimEntity.class, id));
        } catch (Exception e) {
            throw new DataAccessException("Failed to get AnakYatimEntity by ID", e);
        }
    }

    @Override
    public List<AnakYatimEntity> findAll() {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            return session.createQuery("FROM AnakYatimEntity", AnakYatimEntity.class).list();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get all AnakYatimEntity", e);
        }
    }

    @Override
    public void save(AnakYatimEntity entity) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.persist(entity);
        } catch (Exception e) {
            throw new DataAccessException("Failed to save AnakYatimEntity", e);
        }
    }

    @Override
    public void update(AnakYatimEntity entity) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.merge(entity);
        } catch (Exception e) {
            throw new DataAccessException("Failed to update AnakYatimEntity", e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            AnakYatimEntity entity = session.get(AnakYatimEntity.class, id);
            if (entity != null) {
                session.remove(entity);
            }
        } catch (Exception e) {
            throw new DataAccessException("Failed to delete AnakYatimEntity", e);
        }
    }

    @Override
    public boolean exists(String id) {
        return findById(id).isPresent();
    }

    @Override
    public AnakYatimEntity getLastRecord() {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            var query = session.createQuery("FROM AnakYatimEntity ORDER BY id DESC", AnakYatimEntity.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get last AnakYatimEntity", e);
        }
    }

    @Override
    public String getTotal() {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            var count = session.createQuery("SELECT IFNULL(SUM(CAST(e.jumlah AS double)), 0) FROM AnakYatimEntity e", Double.class).uniqueResult();
            return count != null ? String.valueOf(count.longValue()) : "0";
        } catch (Exception e) {
            throw new DataAccessException("Failed to get total AnakYatimEntity", e);
        }
    }
}
