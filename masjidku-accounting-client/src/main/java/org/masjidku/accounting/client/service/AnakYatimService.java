package org.masjidku.accounting.client.service;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import org.masjidku.accounting.client.model.*;
import org.masjidku.accounting.client.model.anakyatim.*;
import org.masjidku.accounting.client.model.operasional.*;
import org.masjidku.accounting.client.model.pembangunan.*;
import org.masjidku.accounting.client.model.tpa.*;
import org.masjidku.accounting.client.model.zakat.*;
public interface AnakYatimService {
    AnakYatim get(String id) throws SQLException;
    ObservableList<AnakYatim> getAll() throws SQLException;
    void save(AnakYatim anakYatim) throws SQLException;
    void update(String[] params) throws SQLException;
    void delete(String id) throws SQLException;
    AnakYatim getLastRecord() throws SQLException;
    String getTotalOutcome() throws SQLException;
    boolean isAnakYatimExist(String id) throws SQLException;
    boolean getConnection();
}
