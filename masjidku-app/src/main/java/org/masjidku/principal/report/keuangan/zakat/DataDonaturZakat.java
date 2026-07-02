/*
 * Copyright (c) 2026. Creative Commons Universal CC0
 */

package org.masjidku.principal.report.keuangan.zakat;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.masjidku.accounting.client.model.zakat.ZakatMasuk;


import org.masjidku.accounting.client.service.AccountingClient;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class DataDonaturZakat extends org.masjidku.accountant.BaseFinanceReportController<ZakatMasuk> {
    private static final Logger log = LoggerFactory.getLogger(DataDonaturZakat.class);
    private final AccountingClient client = ServiceProvider.get(AccountingClient.class);

    @FXML
    private TableView<ZakatMasuk> zakatTable;

    @FXML
    public void gotoHome() {
        mainApp.showZakatData();
    }

    @Override
    protected String getReportTemplatePath() {
        return "/org/masjidku/report/data_donatur_zakat.jrxml";
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

    @Override
    protected TableView<ZakatMasuk> getTableView() {
        return zakatTable;
    }

    @Override
    protected List<ZakatMasuk> fetchAllData() throws java.sql.SQLException {
        return client.getAllZakatMasuk();
    }
}
