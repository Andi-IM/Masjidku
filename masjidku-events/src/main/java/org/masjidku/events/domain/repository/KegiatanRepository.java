package org.masjidku.events.domain.repository;
import javafx.collections.ObservableList;
import org.masjidku.events.client.model.Kegiatan;
import java.sql.SQLException;
public interface KegiatanRepository {
    Kegiatan getKegiatanById(String id) throws SQLException;
    ObservableList<Kegiatan> getAllKegiatan() throws SQLException;
    void saveKegiatan(Kegiatan kegiatan) throws SQLException;
    void updateKegiatan(String[] params) throws SQLException;
    void deleteKegiatan(String id) throws SQLException;
    boolean exists(String id) throws SQLException;
    ObservableList<String> getAllKegiatanNames() throws SQLException;
    String getIdByName(String name) throws SQLException;
    Kegiatan getLastKegiatan() throws SQLException;
    String getTotalKegiatanCount() throws SQLException;
}
