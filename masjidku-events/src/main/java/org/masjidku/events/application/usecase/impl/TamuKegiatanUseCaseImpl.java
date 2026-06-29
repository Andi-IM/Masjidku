package org.masjidku.events.application.usecase.impl;
import javafx.collections.ObservableList;
import org.masjidku.events.application.usecase.TamuKegiatanUseCase;
import org.masjidku.events.domain.entity.TamuKegiatan;
import org.masjidku.events.domain.repository.TamuKegiatanRepository;
import org.masjidku.events.domain.repository.impl.TamuKegiatanRepositoryImpl;
import java.sql.SQLException;

public class TamuKegiatanUseCaseImpl implements TamuKegiatanUseCase {
    private final TamuKegiatanRepository repository;

    public TamuKegiatanUseCaseImpl() { this.repository = new TamuKegiatanRepositoryImpl(); }
    public TamuKegiatanUseCaseImpl(TamuKegiatanRepository repository) {
        this.repository = repository;
    }

    @Override
    public TamuKegiatan getTamuKegiatanById(String id) throws SQLException {
        return repository.getTamuKegiatanById(id);
    }

    @Override
    public ObservableList<TamuKegiatan> getAllTamuKegiatan() throws SQLException {
        return repository.getAllTamuKegiatan();
    }

    @Override
    public void saveTamuKegiatan(String idKegiatan, String idTamu, String keterangan, String operator) throws SQLException {
        repository.saveTamuKegiatan(idKegiatan, idTamu, keterangan, operator);
    }

    @Override
    public void updateTamuKegiatan(String[] params) throws SQLException {
        repository.updateTamuKegiatan(params);
    }

    @Override
    public void deleteTamuKegiatan(String id) throws SQLException {
        repository.deleteTamuKegiatan(id);
    }

    @Override
    public boolean isUndanganExist(String id) throws SQLException {
        return repository.isUndanganExist(id);
    }
}

