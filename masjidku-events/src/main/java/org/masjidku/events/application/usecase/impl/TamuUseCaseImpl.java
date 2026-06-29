package org.masjidku.events.application.usecase.impl;
import javafx.collections.ObservableList;
import org.masjidku.events.client.model.Tamu;
import org.masjidku.events.client.usecase.TamuUseCase;
import org.masjidku.events.domain.repository.TamuRepository;
import org.masjidku.events.domain.repository.impl.TamuRepositoryImpl;

public class TamuUseCaseImpl implements TamuUseCase {
    private final TamuRepository repository;

    public TamuUseCaseImpl() { this.repository = new TamuRepositoryImpl(); }
    public TamuUseCaseImpl(TamuRepository repository) {
        this.repository = repository;
    }

    @Override
    public Tamu get(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID Tamu tidak boleh kosong");
        }
        return repository.get(id);
    }

    @Override
    public ObservableList<Tamu> getAll() {
        return repository.getAll();
    }

    @Override
    public void save(Tamu model) {
        if (model.getNama() == null || model.getNama().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama Tamu tidak boleh kosong");
        }
        repository.save(model);
    }

    @Override
    public void update(String[] params) {
        repository.update(params);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public boolean isTamuExist(String id) {
        return repository.isTamuExist(id);
    }

    @Override
    public ObservableList<String> getAllTamuName() {
        return repository.getAllTamuName();
    }

    @Override
    public String getIdByName(String name) {
        return repository.getIdByName(name);
    }
}

