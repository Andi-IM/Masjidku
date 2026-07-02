/*
 * Copyright (c) 2026. Creative Commons Universal CC0
 */

package org.masjidku.principal.report.keuangan.tpa;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.masjidku.accounting.client.model.tpa.TpaMasuk;


import org.masjidku.accounting.client.service.AccountingClient;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class DataDonaturTpa extends org.masjidku.accountant.BaseFinanceReportController<TpaMasuk> {
    private static final Logger log = LoggerFactory.getLogger(DataDonaturTpa.class);

    @FXML
    private TableView<TpaMasuk> tableTpa;

    @FXML
    public void gotoHome() {
        mainApp.showTpaData();
    }

    @Override
    protected String getReportTemplatePath() {
        return "/org/masjidku/report/data_donatur_tpa.jrxml";
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

    @Override
    protected TableView<TpaMasuk> getTableView() {
        return tableTpa;
    }

    @Override
    protected List<TpaMasuk> fetchAllData() throws java.sql.SQLException {
        return client.getAllTpaMasuk();
    }
}
