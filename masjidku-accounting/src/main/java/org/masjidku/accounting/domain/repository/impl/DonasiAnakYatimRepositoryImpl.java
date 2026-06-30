package org.masjidku.accounting.domain.repository.impl;

import org.masjidku.accounting.domain.entity.DonasiAnakYatimEntity;
import org.masjidku.accounting.domain.repository.DonasiAnakYatimRepository;
import org.masjidku.accounting.domain.repository.base.HibernateUtil;
import org.masjidku.accounting.domain.repository.exception.DataAccessException;
import java.util.List;
import java.util.Optional;

public class DonasiAnakYatimRepositoryImpl implements DonasiAnakYatimRepository {

    @Override
    public Optional<DonasiAnakYatimEntity> findById(String id) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            return Optional.ofNullable(session.get(DonasiAnakYatimEntity.class, id));
        } catch (Exception e) {
            throw new DataAccessException("Failed to get DonasiAnakYatimEntity by ID", e);
        }
    }

    @Override
    public List<DonasiAnakYatimEntity> findAll() {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            return session.createQuery("FROM DonasiAnakYatimEntity", DonasiAnakYatimEntity.class).list();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get all DonasiAnakYatimEntity", e);
        }
    }

    @Override
    public void save(DonasiAnakYatimEntity entity) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.persist(entity);
        } catch (Exception e) {
            throw new DataAccessException("Failed to save DonasiAnakYatimEntity", e);
        }
    }

    @Override
    public void update(DonasiAnakYatimEntity entity) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.merge(entity);
        } catch (Exception e) {
            throw new DataAccessException("Failed to update DonasiAnakYatimEntity", e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            DonasiAnakYatimEntity entity = session.get(DonasiAnakYatimEntity.class, id);
            if (entity != null) {
                session.remove(entity);
            }
        } catch (Exception e) {
            throw new DataAccessException("Failed to delete DonasiAnakYatimEntity", e);
        }
    }

    @Override
    public boolean exists(String id) {
        return findById(id).isPresent();
    }

    @Override
    public DonasiAnakYatimEntity getLastRecord() {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            var query = session.createQuery("FROM DonasiAnakYatimEntity ORDER BY id DESC", DonasiAnakYatimEntity.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get last DonasiAnakYatimEntity", e);
        }
    }

    @Override
    public String getTotal() {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            var count = session.createQuery("SELECT IFNULL(SUM(CAST(e.jumlah AS double)), 0) FROM DonasiAnakYatimEntity e", Double.class).uniqueResult();
            return count != null ? String.valueOf(count.longValue()) : "0";
        } catch (Exception e) {
            throw new DataAccessException("Failed to get total DonasiAnakYatimEntity", e);
        }
    }
}
