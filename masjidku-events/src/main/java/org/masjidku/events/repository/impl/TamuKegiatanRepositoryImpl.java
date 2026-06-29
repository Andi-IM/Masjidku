package org.masjidku.events.repository.impl;

import javafx.collections.ObservableList;
import org.masjidku.events.client.model.TamuKegiatan;
import org.masjidku.events.dao.impl.TamuKegiatanDao;
import org.masjidku.events.repository.TamuKegiatanRepository;

import java.sql.SQLException;

public class TamuKegiatanRepositoryImpl implements TamuKegiatanRepository {
    private final TamuKegiatanDao tamuKegiatanDao;

    public TamuKegiatanRepositoryImpl() {
        this.tamuKegiatanDao = new TamuKegiatanDao();
    }

    public TamuKegiatanRepositoryImpl(TamuKegiatanDao tamuKegiatanDao) {
        this.tamuKegiatanDao = tamuKegiatanDao;
    }

    @Override
    public TamuKegiatan getTamuKegiatanById(String id) throws SQLException {
        return tamuKegiatanDao.get(id);
    }

    @Override
    public ObservableList<TamuKegiatan> getAllTamuKegiatan() throws SQLException {
        return tamuKegiatanDao.getAll();
    }

    @Override
    public void saveTamuKegiatan(String idKegiatan, String idTamu, String keterangan, String operator) throws SQLException {
        tamuKegiatanDao.save(idKegiatan, idTamu, keterangan, operator);
    }

    @Override
    public void updateTamuKegiatan(String[] params) throws SQLException {
        tamuKegiatanDao.update(params);
    }

    @Override
    public void deleteTamuKegiatan(String id) throws SQLException {
        tamuKegiatanDao.delete(id);
    }

    @Override
    public boolean isUndanganExist(String id) throws SQLException {
        return tamuKegiatanDao.isUndanganExist(id);
    }
}
