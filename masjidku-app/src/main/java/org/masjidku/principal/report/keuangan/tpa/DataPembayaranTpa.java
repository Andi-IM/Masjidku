/*
 * Copyright (c) 2026. Creative Commons Universal CC0
 */

package org.masjidku.principal.report.keuangan.tpa;

import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import org.masjidku.accounting.client.model.tpa.TpaKeluar;


import org.masjidku.accounting.client.service.AccountingClient;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class DataPembayaranTpa extends org.masjidku.accountant.BaseFinanceReportController<TpaKeluar> {
    private static final Logger log = LoggerFactory.getLogger(DataPembayaranTpa.class);
    private final AccountingClient client = ServiceProvider.get(AccountingClient.class);

    @FXML
    private TableView<TpaKeluar> tableTpa;

    @FXML
    public void gotoHome() {
        mainApp.showTpaData();
    }



    @Override
    protected String getReportTemplatePath() {
        return "/org/masjidku/report/data_pembayaran_tpa.jrxml";
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

    @Override
    protected TableView<TpaKeluar> getTableView() {
        return tableTpa;
    }

    @Override
    protected List<TpaKeluar> fetchAllData() throws java.sql.SQLException {
        return client.getAllTpaKeluar();
    }
}
