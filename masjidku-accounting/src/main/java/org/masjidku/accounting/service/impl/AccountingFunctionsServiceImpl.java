package org.masjidku.accounting.service.impl;

import org.masjidku.accounting.client.service.AccountingFunctionsService;
import org.masjidku.accounting.dao.DaoFunctions;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.util.List;
import org.masjidku.accounting.client.model.*;
import org.masjidku.accounting.client.model.anakyatim.*;
import org.masjidku.accounting.client.model.operasional.*;
import org.masjidku.accounting.client.model.pembangunan.*;
import org.masjidku.accounting.client.model.tpa.*;
import org.masjidku.accounting.client.model.zakat.*;

public class AccountingFunctionsServiceImpl implements AccountingFunctionsService {
    private final DaoFunctions dao = new DaoFunctions();

    @Override
    public String getInfakYatimBalance() throws SQLException {
        return dao.getInfakYatimBalance();    }
    @Override
    public String getOperationalBalance() throws SQLException {
        return dao.getOperationalBalance();    }
    @Override
    public String getPembangunanBalance() throws SQLException {
        return dao.getPembangunanBalance();    }
    @Override
    public String getTpaBalance() throws SQLException {
        return dao.getTpaBalance();    }
    @Override
    public String getZakatBalance() throws SQLException {
        return dao.getZakatBalance();    }

    @Override
    public boolean getConnection() { return dao.getConnection(); }
}
