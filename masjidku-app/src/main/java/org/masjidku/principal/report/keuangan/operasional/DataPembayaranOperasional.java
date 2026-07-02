/*
 * Copyright (c) 2026. Creative Commons Universal CC0
 */

package org.masjidku.principal.report.keuangan.operasional;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.masjidku.accounting.client.model.operasional.Operasional;


import org.masjidku.accounting.client.service.AccountingClient;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class DataPembayaranOperasional extends org.masjidku.accountant.BaseFinanceReportController<Operasional> {
    private static final Logger log = LoggerFactory.getLogger(DataPembayaranOperasional.class);
    private final AccountingClient client = ServiceProvider.get(AccountingClient.class);

    @FXML
    private TableView<Operasional> tableOperasional;
    @FXML
    private TableColumn<Operasional, String> keterangan;

    @FXML
    public void gotoHome() {
        mainApp.showOperasionalData();
    }

    @Override
    protected void setupTableColumns() {
        super.setupTableColumns();
        keterangan.setCellValueFactory(new PropertyValueFactory<>("keterangan"));
    }

    @Override
    protected String getReportTemplatePath() {
        return "/org/masjidku/report/data_pembayaran_operasional.jrxml";
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

    @Override
    protected TableView<Operasional> getTableView() {
        return tableOperasional;
    }

    @Override
    protected List<Operasional> fetchAllData() throws java.sql.SQLException {
        return client.getAllOperasional();
    }
}
