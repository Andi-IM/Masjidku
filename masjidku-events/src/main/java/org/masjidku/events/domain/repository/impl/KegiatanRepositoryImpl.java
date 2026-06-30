package org.masjidku.events.domain.repository.impl;

import org.masjidku.events.domain.entity.Kegiatan;
import org.masjidku.events.domain.repository.KegiatanRepository;
import org.masjidku.events.domain.repository.base.HibernateUtil;
import org.masjidku.events.domain.repository.exception.DataAccessException;

import java.util.List;
import java.util.Optional;

public class KegiatanRepositoryImpl implements KegiatanRepository {

    @Override
    public Optional<Kegiatan> getKegiatanById(String id) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            return Optional.ofNullable(session.get(Kegiatan.class, id));
        } catch (Exception e) {
            throw new DataAccessException("Failed to get Kegiatan by ID", e);
        }
    }

    @Override
    public List<Kegiatan> getAllKegiatan() {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            return session.createQuery("FROM Kegiatan", Kegiatan.class).list();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get all Kegiatan", e);
        }
    }

    @Override
    public void saveKegiatan(Kegiatan kegiatan) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.persist(kegiatan);
        } catch (Exception e) {
            throw new DataAccessException("Failed to save Kegiatan", e);
        }
    }

    @Override
    public void updateKegiatan(Kegiatan kegiatan) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.merge(kegiatan);
        } catch (Exception e) {
            throw new DataAccessException("Failed to update Kegiatan", e);
        }
    }

    @Override
    public void deleteKegiatan(String id) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            Kegiatan kegiatan = session.get(Kegiatan.class, id);
            if (kegiatan != null) {
                session.remove(kegiatan);
            }
        } catch (Exception e) {
            throw new DataAccessException("Failed to delete Kegiatan", e);
        }
    }

    @Override
    public boolean exists(String id) {
        return getKegiatanById(id).isPresent();
    }

    @Override
    public List<String> getAllKegiatanNames() {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            return session.createQuery("SELECT k.nama FROM Kegiatan k", String.class).list();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get Kegiatan names", e);
        }
    }

    @Override
    public String getIdByName(String name) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            var query = session.createQuery("SELECT k.idKegiatan FROM Kegiatan k WHERE k.nama = :name", String.class);
            query.setParameter("name", name);
            var result = query.uniqueResult();
            return result != null ? result : "";
        } catch (Exception e) {
            throw new DataAccessException("Failed to get Kegiatan ID by name", e);
        }
    }

    @Override
    public Kegiatan getLastKegiatan() {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            var query = session.createQuery("FROM Kegiatan ORDER BY idKegiatan DESC", Kegiatan.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get last Kegiatan", e);
        }
    }

    @Override
    public int getTotalKegiatanCount() {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            var count = session.createQuery("SELECT count(k) FROM Kegiatan k", Long.class).uniqueResult();
            return count != null ? count.intValue() : 0;
        } catch (Exception e) {
            throw new DataAccessException("Failed to get total Kegiatan count", e);
        }
    }
}
