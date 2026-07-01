package org.masjidku.accounting.domain.repository.impl;

import org.hibernate.SessionFactory;
import org.masjidku.accounting.domain.entity.TpaKeluarEntity;
import org.masjidku.accounting.domain.repository.TpaKeluarRepository;
import org.masjidku.accounting.domain.repository.exception.DataAccessException;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class TpaKeluarRepositoryImpl implements TpaKeluarRepository {

    private final SessionFactory sessionFactory;

    @Inject
    public TpaKeluarRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Optional<TpaKeluarEntity> findById(String id) {
        try {
            var session = sessionFactory.getCurrentSession();
            return Optional.ofNullable(session.get(TpaKeluarEntity.class, id));
        } catch (Exception e) {
            throw new DataAccessException("Failed to get TpaKeluarEntity by ID", e);
        }
    }

    @Override
    public List<TpaKeluarEntity> findAll() {
        try {
            var session = sessionFactory.getCurrentSession();
            return session.createQuery("FROM TpaKeluarEntity", TpaKeluarEntity.class).list();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get all TpaKeluarEntity", e);
        }
    }

    @Override
    public void save(TpaKeluarEntity entity) {
        try {
            var session = sessionFactory.getCurrentSession();
            session.persist(entity);
        } catch (Exception e) {
            throw new DataAccessException("Failed to save TpaKeluarEntity", e);
        }
    }

    @Override
    public void update(TpaKeluarEntity entity) {
        try {
            var session = sessionFactory.getCurrentSession();
            session.merge(entity);
        } catch (Exception e) {
            throw new DataAccessException("Failed to update TpaKeluarEntity", e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            var session = sessionFactory.getCurrentSession();
            TpaKeluarEntity entity = session.get(TpaKeluarEntity.class, id);
            if (entity != null) {
                session.remove(entity);
            }
        } catch (Exception e) {
            throw new DataAccessException("Failed to delete TpaKeluarEntity", e);
        }
    }

    @Override
    public boolean exists(String id) {
        return findById(id).isPresent();
    }

    @Override
    public TpaKeluarEntity getLastRecord() {
        try {
            var session = sessionFactory.getCurrentSession();
            var query = session.createQuery("FROM TpaKeluarEntity ORDER BY id DESC", TpaKeluarEntity.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get last TpaKeluarEntity", e);
        }
    }

    @Override
    public String getTotal() {
        try {
            var session = sessionFactory.getCurrentSession();
            var count = session.createQuery("SELECT COALESCE(SUM(e.jumlah), 0) FROM TpaKeluarEntity e", java.math.BigDecimal.class).uniqueResult();
            return count != null ? count.toPlainString() : "0";
        } catch (Exception e) {
            throw new DataAccessException("Failed to get total TpaKeluarEntity", e);
        }
    }
}
