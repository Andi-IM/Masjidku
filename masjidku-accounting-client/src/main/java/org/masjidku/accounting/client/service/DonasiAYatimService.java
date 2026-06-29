package org.masjidku.accounting.client.service;

import javafx.collections.ObservableList;
import org.masjidku.accounting.client.model.anakyatim.DonasiAYatim;

import java.sql.SQLException;

public interface DonasiAYatimService {
    DonasiAYatim get(String id) throws SQLException;

    ObservableList<DonasiAYatim> getAll() throws SQLException;

    void save(DonasiAYatim donasiAYatim) throws SQLException;

    void update(String[] params) throws SQLException;

    void delete(String id);

    DonasiAYatim getLastRecord() throws SQLException;

    String getTotalIncome() throws SQLException;

    boolean isDonaturExist(String id) throws SQLException;

    boolean getConnection();
}
