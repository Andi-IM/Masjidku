package org.masjidku.accounting.domain.repository.impl;

import org.masjidku.accounting.domain.entity.ZakatKeluarEntity;
import org.masjidku.accounting.domain.repository.ZakatKeluarRepository;
import org.masjidku.accounting.domain.repository.base.HibernateUtil;
import org.masjidku.accounting.domain.repository.exception.DataAccessException;
import java.util.List;
import java.util.Optional;

public class ZakatKeluarRepositoryImpl implements ZakatKeluarRepository {

    @Override
    public Optional<ZakatKeluarEntity> findById(String id) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            return Optional.ofNullable(session.get(ZakatKeluarEntity.class, id));
        } catch (Exception e) {
            throw new DataAccessException("Failed to get ZakatKeluarEntity by ID", e);
        }
    }

    @Override
    public List<ZakatKeluarEntity> findAll() {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            return session.createQuery("FROM ZakatKeluarEntity", ZakatKeluarEntity.class).list();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get all ZakatKeluarEntity", e);
        }
    }

    @Override
    public void save(ZakatKeluarEntity entity) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.persist(entity);
        } catch (Exception e) {
            throw new DataAccessException("Failed to save ZakatKeluarEntity", e);
        }
    }

    @Override
    public void update(ZakatKeluarEntity entity) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.merge(entity);
        } catch (Exception e) {
            throw new DataAccessException("Failed to update ZakatKeluarEntity", e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            ZakatKeluarEntity entity = session.get(ZakatKeluarEntity.class, id);
            if (entity != null) {
                session.remove(entity);
            }
        } catch (Exception e) {
            throw new DataAccessException("Failed to delete ZakatKeluarEntity", e);
        }
    }

    @Override
    public boolean exists(String id) {
        return findById(id).isPresent();
    }

    @Override
    public ZakatKeluarEntity getLastRecord() {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            var query = session.createQuery("FROM ZakatKeluarEntity ORDER BY id DESC", ZakatKeluarEntity.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get last ZakatKeluarEntity", e);
        }
    }

    @Override
    public String getTotal() {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            var count = session.createQuery("SELECT IFNULL(SUM(CAST(e.jumlah AS double)), 0) FROM ZakatKeluarEntity e", Double.class).uniqueResult();
            return count != null ? String.valueOf(count.longValue()) : "0";
        } catch (Exception e) {
            throw new DataAccessException("Failed to get total ZakatKeluarEntity", e);
        }
    }
}
