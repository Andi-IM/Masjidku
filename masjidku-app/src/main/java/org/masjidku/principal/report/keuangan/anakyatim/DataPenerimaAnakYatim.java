/*
 * Copyright (c) 2026. Creative Commons Universal CC0
 */

package org.masjidku.principal.report.keuangan.anakyatim;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import org.masjidku.accounting.client.model.anakyatim.AnakYatim;
import org.masjidku.accounting.client.service.AnakYatimService;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class DataPenerimaAnakYatim extends org.masjidku.accountant.BaseFinanceOutflowReportController<AnakYatim> {
    private static final Logger log = LoggerFactory.getLogger(DataPenerimaAnakYatim.class);
    private final AnakYatimService dao = ServiceProvider.get(AnakYatimService.class);

    @FXML
    private TableView<AnakYatim> tableAnakyatim;
    @FXML
    private TableColumn<AnakYatim, String> usia;

    @FXML
    public void gotoHome() {
        mainApp.showAnakYatimData();
    }

    @FXML
    public void printReport() {
        exportPdf(getReportTemplatePath(), "LaporanDataPenerimaAnakYatim.pdf");
    }

    @Override
    protected void setupTableColumns() {
        org.masjidku.util.AlertHelper.setupOutflowColumns(nama, jumlah, tanggal);
        usia.setCellValueFactory(new PropertyValueFactory<>("usia"));
        operator.setCellValueFactory(new PropertyValueFactory<>("operator"));
    }

    @Override
    protected String getReportTemplatePath() {
        return "/org/masjidku/report/data_penerima_anak_yatim.jrxml";
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

    @Override
    protected TableView<AnakYatim> getTableView() {
        return tableAnakyatim;
    }

    @Override
    protected List<AnakYatim> fetchAllData() throws java.sql.SQLException {
        return dao.getAll();
    }
}
