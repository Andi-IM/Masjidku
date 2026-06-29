package org.masjidku.accounting.client.service;

import javafx.collections.ObservableList;
import org.masjidku.accounting.client.model.zakat.ZakatMasuk;

import java.sql.SQLException;

public interface ZakatMasukService {
    ZakatMasuk get(String id) throws SQLException;

    ObservableList<ZakatMasuk> getAll() throws SQLException;

    void save(ZakatMasuk zakatMasuk) throws SQLException;

    void update(String[] params) throws SQLException;

    void delete(String id) throws SQLException;

    ZakatMasuk getLastRecord() throws SQLException;

    String getTotalIncome() throws SQLException;

    boolean isDonaturExist(String id) throws SQLException;

    boolean getConnection();
}
