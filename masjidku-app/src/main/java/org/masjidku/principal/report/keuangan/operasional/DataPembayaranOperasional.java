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
import org.masjidku.navigation.AppRouter;
import org.masjidku.accounting.client.model.operasional.Operasional;
import org.masjidku.accounting.client.service.OperationalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import org.masjidku.reporting.client.service.ReportService;
import org.masjidku.util.ServiceProvider;


public class DataPembayaranOperasional extends org.masjidku.accountant.BaseTableController<Operasional> {
    private static final Logger log = LoggerFactory.getLogger(DataPembayaranOperasional.class);
    private final OperationalService dao = ServiceProvider.get(OperationalService.class);
    @FXML
    private TableView<Operasional> tableOperasional;
    @FXML
    private TableColumn<Operasional, String> nama;
    @FXML
    private TableColumn<Operasional, String> keterangan;
    @FXML
    private TableColumn<Operasional, String> jumlah;
    @FXML
    private TableColumn<Operasional, String> tanggal;
    @FXML
    private TableColumn<Operasional, String> operator;
    private AppRouter mainApp;


    public void setMainApp(AppRouter mainApp) {
        this.mainApp = mainApp;
    }


    @Override
    protected void setupTableColumns() {
        org.masjidku.util.AlertHelper.setupOutflowColumns(nama, jumlah, tanggal);
keterangan.setCellValueFactory(new PropertyValueFactory<>("keterangan"));
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
        ServiceProvider.get(ReportService.class).showReport("/org/masjidku/report/data_pembayaran_operasional.jrxml");
    }

    @Override
    protected org.slf4j.Logger getLogger() {
        return log;
    }

    @Override
    protected TableView<Operasional> getTableView() {
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
    protected List<Operasional> fetchAllData() throws java.sql.SQLException {
        return dao.getAll();
    }

    @Override
    protected boolean checkIfExist(Operasional item) {
        return false;
    }

    @Override
    protected void deleteItem(Operasional item) {
    }

    @Override
    protected void handleEdit(Operasional item) {
    }
}


