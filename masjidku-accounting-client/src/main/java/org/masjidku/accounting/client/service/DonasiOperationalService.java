package org.masjidku.accounting.client.service;

import javafx.collections.ObservableList;
import org.masjidku.accounting.client.model.operasional.DonasiOperasional;

import java.sql.SQLException;

public interface DonasiOperationalService {
    DonasiOperasional get(String id) throws SQLException;

    ObservableList<DonasiOperasional> getAll() throws SQLException;

    void save(DonasiOperasional donasiOperasional) throws SQLException;

    void update(String[] params) throws SQLException;

    void delete(String id) throws SQLException;

    boolean isDonaturExist(String id) throws SQLException;

    DonasiOperasional getLastRecord() throws SQLException;

    String getTotalOutcome() throws SQLException;

    boolean getConnection();
}
