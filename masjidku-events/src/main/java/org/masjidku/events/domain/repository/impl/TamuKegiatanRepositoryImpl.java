package org.masjidku.events.domain.repository.impl;

import org.hibernate.SessionFactory;
import org.masjidku.events.domain.entity.TamuKegiatan;
import org.masjidku.events.domain.repository.TamuKegiatanRepository;
import org.masjidku.events.domain.repository.exception.DataAccessException;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class TamuKegiatanRepositoryImpl implements TamuKegiatanRepository {

    private final SessionFactory sessionFactory;

    @Inject
    public TamuKegiatanRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Optional<TamuKegiatan> getTamuKegiatanById(String id) {
        try {
            var session = sessionFactory.getCurrentSession();
            return Optional.ofNullable(session.get(TamuKegiatan.class, id));
        } catch (Exception e) {
            throw new DataAccessException("Failed to get Undangan by ID", e);
        }
    }

    @Override
    public List<TamuKegiatan> getAllTamuKegiatan() {
        try {
            var session = sessionFactory.getCurrentSession();
            return session.createQuery("FROM TamuKegiatan tk JOIN FETCH tk.tamu JOIN FETCH tk.kegiatan", TamuKegiatan.class).list();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get all Kegiatan", e);
        }
    }

    @Override
    public void save(TamuKegiatan undangan) {
        try {
            var session = sessionFactory.getCurrentSession();
            session.persist(undangan);
        } catch (Exception e) {
            throw new DataAccessException("Failed to save undangan", e);
        }
    }

    @Override
    public void update(TamuKegiatan undangan) {
        try {
            var session = sessionFactory.getCurrentSession();
            session.merge(undangan);
        } catch (Exception e) {
            throw new DataAccessException("Failed to update undangan", e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            var session = sessionFactory.getCurrentSession();
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
