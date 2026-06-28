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

public interface AccountingFunctionsService {
    String getInfakYatimBalance() throws SQLException;
    String getOperationalBalance() throws SQLException;
    String getPembangunanBalance() throws SQLException;
    String getTpaBalance() throws SQLException;
    String getZakatBalance() throws SQLException;
    boolean getConnection();
}
