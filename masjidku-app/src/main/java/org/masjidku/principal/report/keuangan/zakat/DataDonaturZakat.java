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
package org.masjidku.principal.report.keuangan.zakat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import org.masjidku.accountant.BaseTableController;
import java.util.List;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.masjidku.MainApp;
import org.masjidku.accounting.client.model.zakat.ZakatMasuk;
import org.masjidku.accounting.client.service.ZakatMasukService;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataDonaturZakat extends org.masjidku.accountant.BaseTableController<ZakatMasuk> {
    private static final Logger log = LoggerFactory.getLogger(DataDonaturZakat.class);
    private final ZakatMasukService dao = ServiceProvider.get(ZakatMasukService.class);
    @FXML
    private TableView<ZakatMasuk> zakatTable;
    @FXML
    private TableColumn<ZakatMasuk, String> donatur;
    @FXML
    private TableColumn<ZakatMasuk, String> jumlah;
    @FXML
    private TableColumn<ZakatMasuk, String> tanggal;
    @FXML
    private TableColumn<ZakatMasuk, String> operator;
    private MainApp mainApp;
    
    

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    protected void setupTableColumns() {
        donatur.setCellValueFactory(new PropertyValueFactory<>("donatur"));
        jumlah.setCellValueFactory(new PropertyValueFactory<>("jumlah"));
        tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        operator.setCellValueFactory(new PropertyValueFactory<>("operator"));
    }

    

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @FXML
    public void showReport() {
    }

    @FXML
    public void gotoHome() {
        mainApp.showZakatData();
    }

    @Override protected org.slf4j.Logger getLogger() { return log; }
    @Override protected TableView<ZakatMasuk> getTableView() { return zakatTable; }
    @Override protected Button getBtnEdit() { return null; }
    @Override protected Button getBtnRemove() { return null; }
    @Override protected List<ZakatMasuk> fetchAllData() throws java.sql.SQLException { return dao.getAll(); }
    @Override protected boolean checkIfExist(ZakatMasuk item) throws java.sql.SQLException { return false; }
    @Override protected void deleteItem(ZakatMasuk item) throws java.sql.SQLException {  }
    @Override protected void handleEdit(ZakatMasuk item) {  }
}

