package org.masjidku.events.domain.repository.impl;

import org.hibernate.SessionFactory;
import org.masjidku.events.domain.entity.Tamu;
import org.masjidku.events.domain.repository.TamuRepository;
import org.masjidku.events.domain.repository.exception.DataAccessException;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class TamuRepositoryImpl extends BaseEventRepositoryImpl<Tamu> implements TamuRepository {

    @Inject
    public TamuRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Tamu.class, "idTamu", "nama");
    }

    @Override
    public Optional<Tamu> get(String id) {
        return findById(id);
    }

    @Override
    public List<Tamu> getAll() {
        return findAll();
    }

    @Override
    public void save(Tamu tamu) {
        persist(tamu);
    }

    @Override
    public void update(Tamu tamu) {
        merge(tamu);
    }

    @Override
    public void delete(String id) {
        remove(id);
    }

    @Override
    public boolean isTamuExist(String id) {
        return checkExists(id);
    }

    @Override
    public List<String> getAllTamuName() {
        return findAllNames();
    }

    @Override
    public String getIdByName(String name) {
        return findIdByName(name);
    }
}
