package org.masjidku.accounting.client.service;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import org.masjidku.accounting.client.model.*;
import org.masjidku.accounting.client.model.anakyatim.*;
import org.masjidku.accounting.client.model.operasional.*;
import org.masjidku.accounting.client.model.pembangunan.*;
import org.masjidku.accounting.client.model.tpa.*;
import org.masjidku.accounting.client.model.zakat.*;
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
