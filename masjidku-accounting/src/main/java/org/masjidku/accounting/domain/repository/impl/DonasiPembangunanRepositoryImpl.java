package org.masjidku.accounting.domain.repository.impl;

import org.masjidku.accounting.domain.entity.DonasiPembangunanEntity;
import org.masjidku.accounting.domain.repository.DonasiPembangunanRepository;
import org.masjidku.accounting.domain.repository.exception.DataAccessException;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import org.hibernate.SessionFactory;
import org.masjidku.common.TransactionHelper;

public class DonasiPembangunanRepositoryImpl implements DonasiPembangunanRepository {

    private final SessionFactory sessionFactory;

    @Inject
    public DonasiPembangunanRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Optional<DonasiPembangunanEntity> findById(String id) {
        try {
            var session = sessionFactory.getCurrentSession();
            return Optional.ofNullable(session.get(DonasiPembangunanEntity.class, id));
        } catch (Exception e) {
            throw new DataAccessException("Failed to get DonasiPembangunanEntity by ID", e);
        }
    }

    @Override
    public List<DonasiPembangunanEntity> findAll() {
        try {
            var session = sessionFactory.getCurrentSession();
            return session.createQuery("FROM DonasiPembangunanEntity", DonasiPembangunanEntity.class).list();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get all DonasiPembangunanEntity", e);
        }
    }

    @Override
    public void save(DonasiPembangunanEntity entity) {
        try {
            var session = sessionFactory.getCurrentSession();
            session.persist(entity);
        } catch (Exception e) {
            throw new DataAccessException("Failed to save DonasiPembangunanEntity", e);
        }
    }

    @Override
    public void update(DonasiPembangunanEntity entity) {
        try {
            var session = sessionFactory.getCurrentSession();
            session.merge(entity);
        } catch (Exception e) {
            throw new DataAccessException("Failed to update DonasiPembangunanEntity", e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            var session = sessionFactory.getCurrentSession();
            DonasiPembangunanEntity entity = session.get(DonasiPembangunanEntity.class, id);
            if (entity != null) {
                session.remove(entity);
            }
        } catch (Exception e) {
            throw new DataAccessException("Failed to delete DonasiPembangunanEntity", e);
        }
    }

    @Override
    public boolean exists(String id) {
        return findById(id).isPresent();
    }

    @Override
    public DonasiPembangunanEntity getLastRecord() {
        try {
            var session = sessionFactory.getCurrentSession();
            var query = session.createQuery("FROM DonasiPembangunanEntity ORDER BY id DESC", DonasiPembangunanEntity.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get last DonasiPembangunanEntity", e);
        }
    }

    @Override
    public String getTotal() {
        try {
            var session = sessionFactory.getCurrentSession();
            var count = session.createQuery("SELECT IFNULL(SUM(CAST(e.jumlah AS double)), 0) FROM DonasiPembangunanEntity e", Double.class).uniqueResult();
            return count != null ? String.valueOf(count.longValue()) : "0";
        } catch (Exception e) {
            throw new DataAccessException("Failed to get total DonasiPembangunanEntity", e);
        }
    }
}
