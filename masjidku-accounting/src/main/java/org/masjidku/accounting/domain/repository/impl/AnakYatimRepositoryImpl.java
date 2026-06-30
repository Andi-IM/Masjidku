package org.masjidku.accounting.domain.repository.impl;

import org.masjidku.accounting.domain.entity.AnakYatimEntity;
import org.masjidku.accounting.domain.repository.AnakYatimRepository;
import org.masjidku.accounting.domain.repository.exception.DataAccessException;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import org.hibernate.SessionFactory;
import org.masjidku.domain.repository.base.TransactionHelper;

public class AnakYatimRepositoryImpl implements AnakYatimRepository {

    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    @Inject
    public AnakYatimRepositoryImpl(SessionFactory sessionFactory, TransactionHelper transactionHelper) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }


    @Override
    public Optional<AnakYatimEntity> findById(String id) {
        try {
            var session = sessionFactory.getCurrentSession();
            return Optional.ofNullable(session.get(AnakYatimEntity.class, id));
        } catch (Exception e) {
            throw new DataAccessException("Failed to get AnakYatimEntity by ID", e);
        }
    }

    @Override
    public List<AnakYatimEntity> findAll() {
        try {
            var session = sessionFactory.getCurrentSession();
            return session.createQuery("FROM AnakYatimEntity", AnakYatimEntity.class).list();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get all AnakYatimEntity", e);
        }
    }

    @Override
    public void save(AnakYatimEntity entity) {
        try {
            var session = sessionFactory.getCurrentSession();
            session.persist(entity);
        } catch (Exception e) {
            throw new DataAccessException("Failed to save AnakYatimEntity", e);
        }
    }

    @Override
    public void update(AnakYatimEntity entity) {
        try {
            var session = sessionFactory.getCurrentSession();
            session.merge(entity);
        } catch (Exception e) {
            throw new DataAccessException("Failed to update AnakYatimEntity", e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            var session = sessionFactory.getCurrentSession();
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
            var session = sessionFactory.getCurrentSession();
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
            var session = sessionFactory.getCurrentSession();
            var count = session.createQuery("SELECT IFNULL(SUM(CAST(e.jumlah AS double)), 0) FROM AnakYatimEntity e", Double.class).uniqueResult();
            return count != null ? String.valueOf(count.longValue()) : "0";
        } catch (Exception e) {
            throw new DataAccessException("Failed to get total AnakYatimEntity", e);
        }
    }
}
