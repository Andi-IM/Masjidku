package org.masjidku.accounting.client.service;

import javafx.collections.ObservableList;
import org.masjidku.accounting.client.model.pembangunan.Pembangunan;

import java.sql.SQLException;

public interface PembangunanService {
    Pembangunan get(String id) throws SQLException;

    ObservableList<Pembangunan> getAll() throws SQLException;

    void save(Pembangunan pembangunan) throws SQLException;

    void update(String[] params) throws SQLException;

    void delete(String id) throws SQLException;

    Pembangunan getLastRecord() throws SQLException;

    String getTotalIncome() throws SQLException;

    boolean isDataExist(String id) throws SQLException;

    boolean getConnection();
}
