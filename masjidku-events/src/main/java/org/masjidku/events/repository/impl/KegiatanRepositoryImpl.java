package org.masjidku.events.repository.impl;

import javafx.collections.ObservableList;
import org.masjidku.events.client.model.Kegiatan;
import org.masjidku.events.dao.impl.KegiatanDao;
import org.masjidku.events.repository.KegiatanRepository;

import java.sql.SQLException;

public class KegiatanRepositoryImpl implements KegiatanRepository {
    private final KegiatanDao kegiatanDao;

    public KegiatanRepositoryImpl() {
        this.kegiatanDao = new KegiatanDao();
    }

    public KegiatanRepositoryImpl(KegiatanDao kegiatanDao) {
        this.kegiatanDao = kegiatanDao;
    }

    @Override
    public Kegiatan getKegiatanById(String id) throws SQLException {
        return kegiatanDao.get(id);
    }

    @Override
    public ObservableList<Kegiatan> getAllKegiatan() throws SQLException {
        return kegiatanDao.getAll();
    }

    @Override
    public void saveKegiatan(Kegiatan kegiatan) throws SQLException {
        kegiatanDao.save(kegiatan);
    }

    @Override
    public void updateKegiatan(String[] params) throws SQLException {
        kegiatanDao.update(params);
    }

    @Override
    public void deleteKegiatan(String id) throws SQLException {
        kegiatanDao.delete(id);
    }

    @Override
    public boolean exists(String id) throws SQLException {
        return kegiatanDao.isKegiatanExist(id);
    }

    @Override
    public ObservableList<String> getAllKegiatanNames() throws SQLException {
        return kegiatanDao.getAllKegiatanName();
    }

    @Override
    public String getIdByName(String name) throws SQLException {
        return kegiatanDao.getIdByName(name);
    }

    @Override
    public Kegiatan getLastKegiatan() throws SQLException {
        return kegiatanDao.getLastRecord();
    }

    @Override
    public String getTotalKegiatanCount() throws SQLException {
        return kegiatanDao.getTotalKegiatan();
    }
}
