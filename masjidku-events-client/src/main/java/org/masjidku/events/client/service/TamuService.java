package org.masjidku.events.client.service;

import javafx.collections.ObservableList;
import org.masjidku.events.client.model.Tamu;
import org.masjidku.events.client.repository.TamuRepository;
public class TamuService {
    private final TamuRepository repository;

    public TamuService(TamuRepository repository) {
        this.repository = repository;
    }

    public Tamu get(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID Tamu tidak boleh kosong");
        }
        return repository.get(id);
    }

    public ObservableList<Tamu> getAll() {
        return repository.getAll();
    }

    public void save(Tamu model) {
        if (model.getNama() == null || model.getNama().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama Tamu tidak boleh kosong");
        }
        repository.save(model);
    }

    public void update(String[] params) {
        repository.update(params);
    }

    public void delete(String id) {
        repository.delete(id);
    }

    public boolean isTamuExist(String id) {
        return repository.isTamuExist(id);
    }

    public ObservableList<String> getAllTamuName() {
        return repository.getAllTamuName();
    }

    public String getIdByName(String name) {
        return repository.getIdByName(name);
    }
}
