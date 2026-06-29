/*
 * Copyright (c) 2026. Creative Commons Universal CC0
 */

package org.masjidku.principal.report.keuangan.zakat;

import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import org.masjidku.accounting.client.model.zakat.ZakatKeluar;
import org.masjidku.accounting.client.service.ZakatKeluarService;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class DataPenerimaZakat extends org.masjidku.accountant.BaseFinanceReportController<ZakatKeluar> {
    private static final Logger log = LoggerFactory.getLogger(DataPenerimaZakat.class);
    private final ZakatKeluarService dao = ServiceProvider.get(ZakatKeluarService.class);

    @FXML
    private TableView<ZakatKeluar> tableZakat;

    @FXML
    public void gotoHome() {
        mainApp.showZakatData();
    }

    @Override
    protected void setupTableColumns() {
        org.masjidku.util.AlertHelper.setupOutflowColumns(nama, jumlah, tanggal);
        operator.setCellValueFactory(new PropertyValueFactory<>("operator"));
    }

    @Override
    protected String getReportTemplatePath() {
        return "/org/masjidku/report/data_penerima_zakat.jrxml";
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

    @Override
    protected TableView<ZakatKeluar> getTableView() {
        return tableZakat;
    }

    @Override
    protected List<ZakatKeluar> fetchAllData() throws java.sql.SQLException {
        return dao.getAll();
    }
}
