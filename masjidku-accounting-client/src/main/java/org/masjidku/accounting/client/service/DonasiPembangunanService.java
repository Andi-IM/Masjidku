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

public interface DonasiPembangunanService {
    DonasiPembangunan get(String id) throws SQLException;
    ObservableList<DonasiPembangunan> getAll() throws SQLException;
    void save(DonasiPembangunan donasiPembangunan) throws SQLException;
    void update(String[] params) throws SQLException;
    void delete(String id) throws SQLException;
    DonasiPembangunan getLastRecord() throws SQLException;
    String getTotalOutcome() throws SQLException;
    boolean isDonaturExist(String id) throws SQLException;
    boolean getConnection();
}
