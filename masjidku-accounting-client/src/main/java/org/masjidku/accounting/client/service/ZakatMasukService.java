package org.masjidku.accounting.client.service;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import org.masjidku.accounting.client.model.*;
import org.masjidku.accounting.client.model.anakyatim.*;
import org.masjidku.accounting.client.model.operasional.*;
import org.masjidku.accounting.client.model.pembangunan.*;
import org.masjidku.accounting.client.model.tpa.*;
import org.masjidku.accounting.client.model.zakat.*;
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
