package org.masjidku.accounting.client.service;

import javafx.collections.ObservableList;
import org.masjidku.accounting.client.model.operasional.Operasional;

import java.sql.SQLException;

public interface OperationalService {
    Operasional get(String id) throws SQLException;

    ObservableList<Operasional> getAll() throws SQLException;

    void save(Operasional operasional) throws SQLException;

    void update(String[] params) throws SQLException;

    void delete(String id) throws SQLException;

    boolean isDataExist(String id) throws SQLException;

    Operasional getLastRecord() throws SQLException;

    String getTotalIncome() throws SQLException;

    boolean getConnection();
}
