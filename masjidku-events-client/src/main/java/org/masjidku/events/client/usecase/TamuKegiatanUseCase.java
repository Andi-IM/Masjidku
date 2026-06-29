package org.masjidku.events.client.usecase;
import javafx.collections.ObservableList;
import org.masjidku.events.client.model.TamuKegiatan;
import java.sql.SQLException;
public interface TamuKegiatanUseCase {
    TamuKegiatan getTamuKegiatanById(String id) throws SQLException;
    ObservableList<TamuKegiatan> getAllTamuKegiatan() throws SQLException;
    void saveTamuKegiatan(String idKegiatan, String idTamu, String keterangan, String operator) throws SQLException;
    void updateTamuKegiatan(String[] params) throws SQLException;
    void deleteTamuKegiatan(String id) throws SQLException;
    boolean isUndanganExist(String id) throws SQLException;
}
