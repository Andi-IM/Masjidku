package org.masjidku.events.domain.repository.impl;

import org.hibernate.SessionFactory;
import org.masjidku.events.domain.entity.Kegiatan;
import org.masjidku.events.domain.repository.KegiatanRepository;
import org.masjidku.events.domain.repository.exception.DataAccessException;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class KegiatanRepositoryImpl extends BaseEventRepositoryImpl<Kegiatan> implements KegiatanRepository {

    @Inject
    public KegiatanRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Kegiatan.class, "idKegiatan", "nama");
    }

    @Override
    public Optional<Kegiatan> getKegiatanById(String id) {
        return findById(id);
    }

    @Override
    public List<Kegiatan> getAllKegiatan() {
        return findAll();
    }

    @Override
    public void saveKegiatan(Kegiatan kegiatan) {
        persist(kegiatan);
    }

    @Override
    public void updateKegiatan(Kegiatan kegiatan) {
        merge(kegiatan);
    }

    @Override
    public void deleteKegiatan(String id) {
        remove(id);
    }

    @Override
    public boolean exists(String id) {
        return checkExists(id);
    }

    @Override
    public List<String> getAllKegiatanNames() {
        return findAllNames();
    }

    @Override
    public String getIdByName(String name) {
        return findIdByName(name);
    }

    @Override
    public Kegiatan getLastKegiatan() {
        try {
            var session = sessionFactory.getCurrentSession();
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
            var session = sessionFactory.getCurrentSession();
            var count = session.createQuery("SELECT count(k) FROM Kegiatan k", Long.class).uniqueResult();
            return count != null ? count.intValue() : 0;
        } catch (Exception e) {
            throw new DataAccessException("Failed to get total Kegiatan count", e);
        }
    }
}
