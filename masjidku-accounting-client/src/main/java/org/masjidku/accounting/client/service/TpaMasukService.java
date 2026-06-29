package org.masjidku.accounting.client.service;

import javafx.collections.ObservableList;
import org.masjidku.accounting.client.model.tpa.TpaMasuk;

import java.sql.SQLException;

public interface TpaMasukService {
    TpaMasuk get(String id) throws SQLException;

    ObservableList<TpaMasuk> getAll() throws SQLException;

    void save(TpaMasuk tpaMasuk) throws SQLException;

    void update(String[] params) throws SQLException;

    void delete(String id) throws SQLException;

    TpaMasuk getLastRecord() throws SQLException;

    String getTotalIncome() throws SQLException;

    boolean isDonaturExist(String id) throws SQLException;

    boolean getConnection();
}
