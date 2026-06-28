package org.masjidku.events.client.service;

import javafx.collections.ObservableList;
import org.masjidku.events.client.model.Kegiatan;

public interface KegiatanService {
    Kegiatan get(String id) throws java.sql.SQLException;
    ObservableList<Kegiatan> getAll() throws java.sql.SQLException;
    void save(Kegiatan model) throws java.sql.SQLException;
    void update(String[] params) throws java.sql.SQLException;
    void delete(String id) throws java.sql.SQLException;
    boolean isKegiatanExist(String id) throws java.sql.SQLException;
    ObservableList<String> getAllKegiatanName() throws java.sql.SQLException;
    String getIdByName(String name) throws java.sql.SQLException;
    Kegiatan getLastRecord() throws java.sql.SQLException;
    String getTotalKegiatan() throws java.sql.SQLException;
}
