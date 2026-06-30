package org.masjidku.events.domain.repository.impl;

import org.masjidku.events.domain.entity.TamuKegiatan;
import org.masjidku.events.domain.repository.TamuKegiatanRepository;
import org.masjidku.events.domain.repository.base.HibernateUtil;
import org.masjidku.events.domain.repository.exception.DataAccessException;

import java.util.List;
import java.util.Optional;

public class TamuKegiatanRepositoryImpl implements TamuKegiatanRepository {

    @Override
    public Optional<TamuKegiatan> getTamuKegiatanById(String id) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            return Optional.ofNullable(session.get(TamuKegiatan.class, id));
        } catch (Exception e) {
            throw new DataAccessException("Failed to get Undangan by ID", e);
        }
    }

    @Override
    public List<TamuKegiatan> getAllTamuKegiatan() {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            var list = session.createQuery("FROM TamuKegiatan tk JOIN FETCH tk.tamu JOIN FETCH tk.kegiatan", TamuKegiatan.class).list();
            return javafx.collections.FXCollections.observableArrayList(list);
        } catch (Exception e) {
            throw new DataAccessException("Failed to get all Kegiatan", e);
        }
    }

    @Override
    public void save(TamuKegiatan undangan) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.persist(undangan);
        } catch (Exception e) {
            throw new DataAccessException("Failed to save undangan", e);
        }
    }

    @Override
    public void update(TamuKegiatan undangan) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.merge(undangan);
        } catch (Exception e) {
            throw new DataAccessException("Failed to update undangan", e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            TamuKegiatan tk = session.get(TamuKegiatan.class, id);
            if (tk != null) {
                session.remove(tk);
            }
        } catch (Exception e) {
            throw new DataAccessException("Failed to delete undangan", e);
        }
    }

    @Override
    public boolean isUndanganExist(String id) {
        return getTamuKegiatanById(id).isPresent();
    }
}
