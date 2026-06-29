package org.masjidku.events.application.usecase.impl;

import javafx.collections.ObservableList;
import org.masjidku.events.application.usecase.KegiatanUseCase;
import org.masjidku.events.client.model.Kegiatan;
import org.masjidku.events.domain.repository.KegiatanRepository;
import org.masjidku.events.domain.repository.impl.KegiatanRepositoryImpl;

import java.sql.SQLException;

public class KegiatanUseCaseImpl implements KegiatanUseCase {
    private final KegiatanRepository repository;

    public KegiatanUseCaseImpl() {
        this.repository = new KegiatanRepositoryImpl();
    }

    public KegiatanUseCaseImpl(KegiatanRepository repository) {
        this.repository = repository;
    }

    @Override
    public Kegiatan getKegiatanById(String id) throws SQLException {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID Kegiatan tidak boleh kosong");
        }
        return repository.getKegiatanById(id);
    }

    @Override
    public ObservableList<Kegiatan> getAllKegiatan() throws SQLException {
        return repository.getAllKegiatan();
    }

    @Override
    public void saveKegiatan(Kegiatan kegiatan) throws SQLException {
        if (kegiatan.getNama() == null || kegiatan.getNama().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama Kegiatan tidak boleh kosong");
        }
        repository.saveKegiatan(kegiatan);
    }

    @Override
    public void updateKegiatan(String[] params) throws SQLException {
        repository.updateKegiatan(params);
    }

    @Override
    public void deleteKegiatan(String id) throws SQLException {
        repository.deleteKegiatan(id);
    }

    @Override
    public boolean exists(String id) throws SQLException {
        return repository.exists(id);
    }

    @Override
    public ObservableList<String> getAllKegiatanNames() throws SQLException {
        return repository.getAllKegiatanNames();
    }

    @Override
    public String getIdByName(String name) throws SQLException {
        return repository.getIdByName(name);
    }

    @Override
    public Kegiatan getLastKegiatan() throws SQLException {
        return repository.getLastKegiatan();
    }

    @Override
    public String getTotalKegiatanCount() throws SQLException {
        return repository.getTotalKegiatanCount();
    }
}

