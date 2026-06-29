/*
 * Copyright (c) 2026. Creative Commons Universal CC0
 */

package org.masjidku.principal.report.keuangan.anakyatim;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.masjidku.accounting.client.model.anakyatim.DonasiAYatim;
import org.masjidku.accounting.client.service.DonasiAYatimService;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class DataDonaturAnakYatim extends org.masjidku.accountant.BaseFinanceReportController<DonasiAYatim> {
    private static final Logger log = LoggerFactory.getLogger(DataDonaturAnakYatim.class);
    private final DonasiAYatimService dao = ServiceProvider.get(DonasiAYatimService.class);

    @FXML
    private TableView<DonasiAYatim> tblAYMasuk;

    @FXML
    public void gotoHome() {
        mainApp.showAnakYatimData();
    }

    @FXML
    public void printReport() {
        exportPdf(getReportTemplatePath(), "LaporanDataDonaturAnakYatim.pdf");
    }

    @Override
    protected String getReportTemplatePath() {
        return "/org/masjidku/report/data_donatur_anak_yatim.jrxml";
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

    @Override
    protected TableView<DonasiAYatim> getTableView() {
        return tblAYMasuk;
    }

    @Override
    protected List<DonasiAYatim> fetchAllData() throws java.sql.SQLException {
        return dao.getAll();
    }
}
