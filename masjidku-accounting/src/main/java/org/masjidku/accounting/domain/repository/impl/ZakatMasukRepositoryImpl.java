package org.masjidku.accounting.domain.repository.impl;

import org.masjidku.accounting.domain.entity.ZakatMasukEntity;
import org.masjidku.accounting.domain.repository.ZakatMasukRepository;
import org.masjidku.accounting.domain.repository.exception.DataAccessException;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import org.hibernate.SessionFactory;
import org.masjidku.domain.repository.base.TransactionHelper;

public class ZakatMasukRepositoryImpl implements ZakatMasukRepository {

    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    @Inject
    public ZakatMasukRepositoryImpl(SessionFactory sessionFactory, TransactionHelper transactionHelper) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }


    @Override
    public Optional<ZakatMasukEntity> findById(String id) {
        try {
            var session = sessionFactory.getCurrentSession();
            return Optional.ofNullable(session.get(ZakatMasukEntity.class, id));
        } catch (Exception e) {
            throw new DataAccessException("Failed to get ZakatMasukEntity by ID", e);
        }
    }

    @Override
    public List<ZakatMasukEntity> findAll() {
        try {
            var session = sessionFactory.getCurrentSession();
            return session.createQuery("FROM ZakatMasukEntity", ZakatMasukEntity.class).list();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get all ZakatMasukEntity", e);
        }
    }

    @Override
    public void save(ZakatMasukEntity entity) {
        try {
            var session = sessionFactory.getCurrentSession();
            session.persist(entity);
        } catch (Exception e) {
            throw new DataAccessException("Failed to save ZakatMasukEntity", e);
        }
    }

    @Override
    public void update(ZakatMasukEntity entity) {
        try {
            var session = sessionFactory.getCurrentSession();
            session.merge(entity);
        } catch (Exception e) {
            throw new DataAccessException("Failed to update ZakatMasukEntity", e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            var session = sessionFactory.getCurrentSession();
            ZakatMasukEntity entity = session.get(ZakatMasukEntity.class, id);
            if (entity != null) {
                session.remove(entity);
            }
        } catch (Exception e) {
            throw new DataAccessException("Failed to delete ZakatMasukEntity", e);
        }
    }

    @Override
    public boolean exists(String id) {
        return findById(id).isPresent();
    }

    @Override
    public ZakatMasukEntity getLastRecord() {
        try {
            var session = sessionFactory.getCurrentSession();
            var query = session.createQuery("FROM ZakatMasukEntity ORDER BY id DESC", ZakatMasukEntity.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get last ZakatMasukEntity", e);
        }
    }

    @Override
    public String getTotal() {
        try {
            var session = sessionFactory.getCurrentSession();
            var count = session.createQuery("SELECT IFNULL(SUM(CAST(e.jumlah AS double)), 0) FROM ZakatMasukEntity e", Double.class).uniqueResult();
            return count != null ? String.valueOf(count.longValue()) : "0";
        } catch (Exception e) {
            throw new DataAccessException("Failed to get total ZakatMasukEntity", e);
        }
    }
}
