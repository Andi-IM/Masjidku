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

package org.masjidku.principal.report.keuangan.pembangunan;

import javafx.fxml.FXML;

import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import org.masjidku.navigation.AppRouter;
import org.masjidku.accounting.client.model.pembangunan.DonasiPembangunan;
import org.masjidku.accounting.client.service.DonasiPembangunanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.masjidku.reporting.client.service.ReportService;
import org.masjidku.util.ServiceProvider;


public class DataDonaturPembangunan extends org.masjidku.accountant.BaseTableController<DonasiPembangunan> {
    private static final Logger log = LoggerFactory.getLogger(DataDonaturPembangunan.class);
    private final DonasiPembangunanService dao = ServiceProvider.get(DonasiPembangunanService.class);
    @FXML
    private TableView<DonasiPembangunan> tablePembangunan;
    @FXML
    private TableColumn<DonasiPembangunan, String> donatur;
    @FXML
    private TableColumn<DonasiPembangunan, String> jumlah;
    @FXML
    private TableColumn<DonasiPembangunan, String> tanggal;
    @FXML
    private TableColumn<DonasiPembangunan, String> operator;

    private AppRouter mainApp;

    


    public void setMainApp(AppRouter mainApp) {
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
    public void showReport() {
        ServiceProvider.get(ReportService.class).showReport("/org/masjidku/report/data_donatur_pembangunan.jrxml");
    }

    @FXML
    public void gotoHome() {
        mainApp.showPembangunanData();
    }

    @Override protected org.slf4j.Logger getLogger() { return log; }
    @Override protected TableView<DonasiPembangunan> getTableView() { return tablePembangunan; }
    @Override protected Button getBtnEdit() { return null; }
    @Override protected Button getBtnRemove() { return null; }
    @Override protected List<DonasiPembangunan> fetchAllData() throws java.sql.SQLException { return dao.getAll(); }
    @Override protected boolean checkIfExist(DonasiPembangunan item) { return false; }
    @Override protected void deleteItem(DonasiPembangunan item) {  }
    @Override protected void handleEdit(DonasiPembangunan item) {  }
}


