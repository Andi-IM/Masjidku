package org.masjidku.accounting.domain.repository.impl;

import org.hibernate.SessionFactory;
import org.masjidku.accounting.domain.entity.DonasiOperasionalEntity;
import org.masjidku.accounting.domain.repository.DonasiOperasionalRepository;
import org.masjidku.accounting.domain.repository.exception.DataAccessException;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class DonasiOperasionalRepositoryImpl implements DonasiOperasionalRepository {

    private final SessionFactory sessionFactory;

    @Inject
    public DonasiOperasionalRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Optional<DonasiOperasionalEntity> findById(String id) {
        try {
            var session = sessionFactory.getCurrentSession();
            return Optional.ofNullable(session.get(DonasiOperasionalEntity.class, id));
        } catch (Exception e) {
            throw new DataAccessException("Failed to get DonasiOperasionalEntity by ID", e);
        }
    }

    @Override
    public List<DonasiOperasionalEntity> findAll() {
        try {
            var session = sessionFactory.getCurrentSession();
            return session.createQuery("FROM DonasiOperasionalEntity", DonasiOperasionalEntity.class).list();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get all DonasiOperasionalEntity", e);
        }
    }

    @Override
    public void save(DonasiOperasionalEntity entity) {
        try {
            var session = sessionFactory.getCurrentSession();
            session.persist(entity);
        } catch (Exception e) {
            throw new DataAccessException("Failed to save DonasiOperasionalEntity", e);
        }
    }

    @Override
    public void update(DonasiOperasionalEntity entity) {
        try {
            var session = sessionFactory.getCurrentSession();
            session.merge(entity);
        } catch (Exception e) {
            throw new DataAccessException("Failed to update DonasiOperasionalEntity", e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            var session = sessionFactory.getCurrentSession();
            DonasiOperasionalEntity entity = session.get(DonasiOperasionalEntity.class, id);
            if (entity != null) {
                session.remove(entity);
            }
        } catch (Exception e) {
            throw new DataAccessException("Failed to delete DonasiOperasionalEntity", e);
        }
    }

    @Override
    public boolean exists(String id) {
        return findById(id).isPresent();
    }

    @Override
    public DonasiOperasionalEntity getLastRecord() {
        try {
            var session = sessionFactory.getCurrentSession();
            var query = session.createQuery("FROM DonasiOperasionalEntity ORDER BY id DESC", DonasiOperasionalEntity.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get last DonasiOperasionalEntity", e);
        }
    }

    @Override
    public String getTotal() {
        try {
            var session = sessionFactory.getCurrentSession();
            var count = session.createQuery("SELECT IFNULL(SUM(CAST(e.jumlah AS double)), 0) FROM DonasiOperasionalEntity e", Double.class).uniqueResult();
            return count != null ? String.valueOf(count.longValue()) : "0";
        } catch (Exception e) {
            throw new DataAccessException("Failed to get total DonasiOperasionalEntity", e);
        }
    }
}
