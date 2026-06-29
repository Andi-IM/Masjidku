package org.masjidku.events.domain.repository;
import javafx.collections.ObservableList;
import org.masjidku.events.domain.entity.TamuKegiatan;
import java.sql.SQLException;
public interface TamuKegiatanRepository {
    TamuKegiatan getTamuKegiatanById(String id) throws SQLException;
    ObservableList<TamuKegiatan> getAllTamuKegiatan() throws SQLException;
    void saveTamuKegiatan(String idKegiatan, String idTamu, String keterangan, String operator) throws SQLException;
    void updateTamuKegiatan(String[] params) throws SQLException;
    void deleteTamuKegiatan(String id) throws SQLException;
    boolean isUndanganExist(String id) throws SQLException;
}
