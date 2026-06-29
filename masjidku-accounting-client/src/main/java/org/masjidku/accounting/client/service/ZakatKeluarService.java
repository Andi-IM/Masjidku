package org.masjidku.accounting.client.service;

import javafx.collections.ObservableList;
import org.masjidku.accounting.client.model.zakat.ZakatKeluar;

import java.sql.SQLException;

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
