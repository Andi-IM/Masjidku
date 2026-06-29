/*
 * Copyright (c) 2026. Creative Commons Universal CC0
 */

package org.masjidku.principal.report.keuangan.pembangunan;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.masjidku.accounting.client.model.pembangunan.Pembangunan;
import org.masjidku.accounting.client.service.PembangunanService;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class DataPembayaranPembangunan extends org.masjidku.accountant.BaseFinanceReportController<Pembangunan> {
    private static final Logger log = LoggerFactory.getLogger(DataPembayaranPembangunan.class);
    private final PembangunanService dao = ServiceProvider.get(PembangunanService.class);

    @FXML
    private TableView<Pembangunan> tablePembangunan;
    @FXML
    private TableColumn<Pembangunan, String> keterangan;

    @FXML
    public void gotoHome() {
        mainApp.showPembangunanData();
    }

    @Override
    protected void setupTableColumns() {
        super.setupTableColumns();
        keterangan.setCellValueFactory(new PropertyValueFactory<>("keterangan"));
    }

    @Override
    protected String getReportTemplatePath() {
        return "/org/masjidku/report/data_pembayaran_pembangunan.jrxml";
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

    @Override
    protected TableView<Pembangunan> getTableView() {
        return tablePembangunan;
    }

    @Override
    protected List<Pembangunan> fetchAllData() throws java.sql.SQLException {
        return dao.getAll();
    }
}
