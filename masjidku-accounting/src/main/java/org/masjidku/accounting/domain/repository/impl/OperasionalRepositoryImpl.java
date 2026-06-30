package org.masjidku.accounting.domain.repository.impl;

import org.hibernate.SessionFactory;
import org.masjidku.accounting.domain.entity.OperasionalEntity;
import org.masjidku.accounting.domain.repository.OperasionalRepository;
import org.masjidku.accounting.domain.repository.exception.DataAccessException;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class OperasionalRepositoryImpl implements OperasionalRepository {

    private final SessionFactory sessionFactory;

    @Inject
    public OperasionalRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Optional<OperasionalEntity> findById(String id) {
        try {
            var session = sessionFactory.getCurrentSession();
            return Optional.ofNullable(session.get(OperasionalEntity.class, id));
        } catch (Exception e) {
            throw new DataAccessException("Failed to get OperasionalEntity by ID", e);
        }
    }

    @Override
    public List<OperasionalEntity> findAll() {
        try {
            var session = sessionFactory.getCurrentSession();
            return session.createQuery("FROM OperasionalEntity", OperasionalEntity.class).list();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get all OperasionalEntity", e);
        }
    }

    @Override
    public void save(OperasionalEntity entity) {
        try {
            var session = sessionFactory.getCurrentSession();
            session.persist(entity);
        } catch (Exception e) {
            throw new DataAccessException("Failed to save OperasionalEntity", e);
        }
    }

    @Override
    public void update(OperasionalEntity entity) {
        try {
            var session = sessionFactory.getCurrentSession();
            session.merge(entity);
        } catch (Exception e) {
            throw new DataAccessException("Failed to update OperasionalEntity", e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            var session = sessionFactory.getCurrentSession();
            OperasionalEntity entity = session.get(OperasionalEntity.class, id);
            if (entity != null) {
                session.remove(entity);
            }
        } catch (Exception e) {
            throw new DataAccessException("Failed to delete OperasionalEntity", e);
        }
    }

    @Override
    public boolean exists(String id) {
        return findById(id).isPresent();
    }

    @Override
    public OperasionalEntity getLastRecord() {
        try {
            var session = sessionFactory.getCurrentSession();
            var query = session.createQuery("FROM OperasionalEntity ORDER BY id DESC", OperasionalEntity.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get last OperasionalEntity", e);
        }
    }

    @Override
    public String getTotal() {
        try {
            var session = sessionFactory.getCurrentSession();
            var count = session.createQuery("SELECT IFNULL(SUM(CAST(e.jumlah AS double)), 0) FROM OperasionalEntity e", Double.class).uniqueResult();
            return count != null ? String.valueOf(count.longValue()) : "0";
        } catch (Exception e) {
            throw new DataAccessException("Failed to get total OperasionalEntity", e);
        }
    }
}
