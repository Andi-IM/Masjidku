package org.masjidku.events.domain.repository.impl;

import org.hibernate.SessionFactory;
import org.masjidku.events.domain.entity.Tamu;
import org.masjidku.events.domain.repository.TamuRepository;
import org.masjidku.events.domain.repository.exception.DataAccessException;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class TamuRepositoryImpl implements TamuRepository {

    private final SessionFactory sessionFactory;

    @Inject
    public TamuRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Optional<Tamu> get(String id) {
        try {
            var session = sessionFactory.getCurrentSession();
            return Optional.ofNullable(session.get(Tamu.class, id));
        } catch (Exception e) {
            throw new DataAccessException("Failed to get Tamu by ID", e);
        }
    }

    @Override
    public List<Tamu> getAll() {
        try {
            var session = sessionFactory.getCurrentSession();
            return session.createQuery("FROM Tamu", Tamu.class).list();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get all Tamu", e);
        }
    }

    @Override
    public void save(Tamu tamu) {
        try {
            var session = sessionFactory.getCurrentSession();
            session.persist(tamu);
        } catch (Exception e) {
            throw new DataAccessException("Failed to save Tamu", e);
        }
    }

    @Override
    public void update(Tamu tamu) {
        try {
            var session = sessionFactory.getCurrentSession();
            session.merge(tamu);
        } catch (Exception e) {
            throw new DataAccessException("Failed to update Tamu", e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            var session = sessionFactory.getCurrentSession();
            Tamu tamu = session.get(Tamu.class, id);
            if (tamu != null) {
                session.remove(tamu);
            }
        } catch (Exception e) {
            throw new DataAccessException("Failed to delete Tamu", e);
        }
    }

    @Override
    public boolean isTamuExist(String id) {
        return get(id).isPresent();
    }

    @Override
    public List<String> getAllTamuName() {
        try {
            var session = sessionFactory.getCurrentSession();
            return session.createQuery("SELECT t.nama FROM Tamu t", String.class).list();
        } catch (Exception e) {
            throw new DataAccessException("Failed to get Tamu names", e);
        }
    }

    @Override
    public String getIdByName(String name) {
        try {
            var session = sessionFactory.getCurrentSession();
            var query = session.createQuery("SELECT t.idTamu FROM Tamu t WHERE t.nama = :name", String.class);
            query.setParameter("name", name);
            var result = query.uniqueResult();
            return result != null ? result : "";
        } catch (Exception e) {
            throw new DataAccessException("Failed to get Tamu ID by name", e);
        }
    }
}
