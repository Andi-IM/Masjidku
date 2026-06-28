package org.masjidku.accounting.client.service;

import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.util.List;
import org.masjidku.accounting.client.model.*;
import org.masjidku.accounting.client.model.anakyatim.*;
import org.masjidku.accounting.client.model.operasional.*;
import org.masjidku.accounting.client.model.pembangunan.*;
import org.masjidku.accounting.client.model.tpa.*;
import org.masjidku.accounting.client.model.zakat.*;

public interface ZakatKeluarService {
    ZakatKeluar get(String id) throws SQLException;
    ObservableList<ZakatKeluar> getAll() throws SQLException;
    void save(ZakatKeluar zakatKeluar) throws SQLException;
    void update(String[] params) throws SQLException;
    void delete(String id) throws SQLException;
    ZakatKeluar getLastRecord() throws SQLException;
    String gettotalOutcome() throws SQLException;
    boolean isDataExist(String id) throws SQLException;
    boolean getConnection();
}
