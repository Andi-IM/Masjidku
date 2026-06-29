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

import javafx.fxml.FXML;

import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import org.masjidku.navigation.AppRouter;
import org.masjidku.accounting.client.model.zakat.ZakatMasuk;
import org.masjidku.accounting.client.service.ZakatMasukService;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    @Override protected boolean checkIfExist(ZakatMasuk item) { return false; }
    @Override protected void deleteItem(ZakatMasuk item) {  }
    @Override protected void handleEdit(ZakatMasuk item) {  }
}


