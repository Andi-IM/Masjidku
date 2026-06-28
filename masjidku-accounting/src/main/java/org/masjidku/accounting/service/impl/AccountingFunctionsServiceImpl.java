package org.masjidku.accounting.service.impl;

import org.masjidku.accounting.client.service.AccountingFunctionsService;
import org.masjidku.accounting.dao.DaoFunctions;

import java.sql.SQLException;

public class AccountingFunctionsServiceImpl implements AccountingFunctionsService {
    private final DaoFunctions dao = new DaoFunctions();

    @Override
    public String getInfakYatimBalance() throws SQLException {
        return dao.getInfakYatimBalance();
    }

    @Override
    public String getOperationalBalance() throws SQLException {
        return dao.getOperationalBalance();
    }

    @Override
    public String getPembangunanBalance() throws SQLException {
        return dao.getPembangunanBalance();
    }

    @Override
    public String getTpaBalance() throws SQLException {
        return dao.getTpaBalance();
    }

    @Override
    public String getZakatBalance() throws SQLException {
        return dao.getZakatBalance();
    }

    @Override
    public boolean getConnection() {
        return dao.getConnection();
    }
}
