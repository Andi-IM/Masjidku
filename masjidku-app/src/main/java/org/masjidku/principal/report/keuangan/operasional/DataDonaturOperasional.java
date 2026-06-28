/*
 * Copyright (c) 2021. Creative Commons Legal Code
 *
 *                            CC0 1.0 Universal
 *
 *                                CREATIVE COMMONS CORPORATION IS NOT A LAW FIRM AND DOES NOT PROVIDE
 *                                LEGAL SERVICES. DISTRIBUTION OF THIS DOCUMENT DOES NOT CREATE AN
 *                                ATTORNEY-CLIENT RELATIONSHIP. CREATIVE COMMONS PROVIDES THIS
 *                                INFORMATION ON AN "AS-IS" BASIS. CREATIVE COMMONS MAKES NO WARRANTIES
 *                                REGARDING THE USE OF THIS DOCUMENT OR THE INFORMATION OR WORKS
 *                                PROVIDED HEREUNDER, AND DISCLAIMS LIABILITY FOR DAMAGES RESULTING FROM
 *                                THE USE OF THIS DOCUMENT OR THE INFORMATION OR WORKS PROVIDED
 *                                HEREUNDER.
 */

package org.masjidku.principal.report.keuangan.operasional;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.masjidku.MainApp;
import org.masjidku.accounting.client.model.operasional.DonasiOperasional;
import org.masjidku.accounting.client.service.DonasiOperationalService;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DataDonaturOperasional extends org.masjidku.accountant.BaseTableController<DonasiOperasional> {
    private static final Logger log = LoggerFactory.getLogger(DataDonaturOperasional.class);
    private final DonasiOperationalService dao = ServiceProvider.get(DonasiOperationalService.class);
    @FXML
    private TableView<DonasiOperasional> tableOperasional;
    @FXML
    private TableColumn<DonasiOperasional, String> donatur;
    @FXML
    private TableColumn<DonasiOperasional, String> jumlah;
    @FXML
    private TableColumn<DonasiOperasional, String> tanggal;
    @FXML
    private TableColumn<DonasiOperasional, String> operator;

    private MainApp mainApp;


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


    @Override
    protected void setupTableColumns() {
        org.masjidku.util.AlertHelper.setupInflowColumns(donatur, jumlah, tanggal);
        operator.setCellValueFactory(new PropertyValueFactory<>("operator"));
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @FXML
    public void gotoHome() {
        mainApp.showOperasionalData();
    }

    @FXML
    public void showReport() {
    }

    @Override
    protected org.slf4j.Logger getLogger() {
        return log;
    }

    @Override
    protected TableView<DonasiOperasional> getTableView() {
        return tableOperasional;
    }

    @Override
    protected Button getBtnEdit() {
        return null;
    }

    @Override
    protected Button getBtnRemove() {
        return null;
    }

    @Override
    protected List<DonasiOperasional> fetchAllData() throws java.sql.SQLException {
        return dao.getAll();
    }

    @Override
    protected boolean checkIfExist(DonasiOperasional item) {
        return false;
    }

    @Override
    protected void deleteItem(DonasiOperasional item) {
    }

    @Override
    protected void handleEdit(DonasiOperasional item) {
    }
}

