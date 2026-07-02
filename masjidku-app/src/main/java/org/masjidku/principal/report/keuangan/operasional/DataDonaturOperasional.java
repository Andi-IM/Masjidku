/*
 * Copyright (c) 2026. Creative Commons Universal CC0
 */

package org.masjidku.principal.report.keuangan.operasional;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.masjidku.accounting.client.model.operasional.DonasiOperasional;


import org.masjidku.accounting.client.service.AccountingClient;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class DataDonaturOperasional extends org.masjidku.accountant.BaseFinanceReportController<DonasiOperasional> {
    private static final Logger log = LoggerFactory.getLogger(DataDonaturOperasional.class);

    @FXML
    private TableView<DonasiOperasional> tableOperasional;

    @FXML
    public void gotoHome() {
        mainApp.showOperasionalData();
    }

    @Override
    protected String getReportTemplatePath() {
        return "/org/masjidku/report/data_donatur_operasional.jrxml";
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

    @Override
    protected TableView<DonasiOperasional> getTableView() {
        return tableOperasional;
    }

    @Override
    protected List<DonasiOperasional> fetchAllData() throws java.sql.SQLException {
        return client.getAllDonasiOperasional();
    }
}
