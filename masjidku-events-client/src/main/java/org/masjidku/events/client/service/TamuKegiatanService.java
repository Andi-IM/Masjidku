package org.masjidku.events.client.service;

import javafx.collections.ObservableList;
import org.masjidku.events.client.model.TamuKegiatan;

public interface TamuKegiatanService {
    TamuKegiatan get(String id) throws java.sql.SQLException;
    ObservableList<TamuKegiatan> getAll() throws java.sql.SQLException;
    void save(TamuKegiatan model) throws java.sql.SQLException;
    void save(String idKegiatan, String idTamu, String keterangan, String opeartor) throws java.sql.SQLException;
    void update(String[] params) throws java.sql.SQLException;
    void delete(String id) throws java.sql.SQLException;
    boolean isUndanganExist(String id) throws java.sql.SQLException;
}
