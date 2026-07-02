/*
 * Copyright (c) 2026. Creative Commons Universal CC0
 */

package org.masjidku.principal.report.keuangan.pembangunan;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.masjidku.accounting.client.model.pembangunan.DonasiPembangunan;


import org.masjidku.accounting.client.service.AccountingClient;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class DataDonaturPembangunan extends org.masjidku.accountant.BaseFinanceReportController<DonasiPembangunan> {
    private static final Logger log = LoggerFactory.getLogger(DataDonaturPembangunan.class);

    @FXML
    private TableView<DonasiPembangunan> tablePembangunan;

    @FXML
    public void gotoHome() {
        mainApp.showPembangunanData();
    }

    @Override
    protected String getReportTemplatePath() {
        return "/org/masjidku/report/data_donatur_pembangunan.jrxml";
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

    @Override
    protected TableView<DonasiPembangunan> getTableView() {
        return tablePembangunan;
    }

    @Override
    protected List<DonasiPembangunan> fetchAllData() throws java.sql.SQLException {
        return client.getAllDonasiPembangunan();
    }
}
