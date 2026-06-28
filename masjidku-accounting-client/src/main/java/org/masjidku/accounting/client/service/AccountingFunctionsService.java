package org.masjidku.accounting.client.service;

import java.sql.SQLException;

public interface AccountingFunctionsService {
    String getInfakYatimBalance() throws SQLException;

    String getOperationalBalance() throws SQLException;

    String getPembangunanBalance() throws SQLException;

    String getTpaBalance() throws SQLException;

    String getZakatBalance() throws SQLException;

    boolean getConnection();
}
