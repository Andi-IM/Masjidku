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

package org.masjidku.accountant.tpa;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.masjidku.controller.BaseTableController;
import org.masjidku.navigation.AppRouter;
import org.masjidku.accounting.client.model.tpa.TpaMasuk;


import org.masjidku.accounting.client.service.AccountingClient;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DonaturTpa extends BaseTableController<TpaMasuk> {
    private static final Logger log = LoggerFactory.getLogger(DonaturTpa.class);
    private final AccountingClient client = ServiceProvider.get(AccountingClient.class);

    @FXML
    private TableView<TpaMasuk> tableTpa;
    @FXML
    private TableColumn<TpaMasuk, String> donatur;
    @FXML
    private TableColumn<TpaMasuk, String> jumlah;
    @FXML
    private TableColumn<TpaMasuk, String> tanggal;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnRemove;

    private AppRouter mainApp;

    

    

    public void setMainApp(AppRouter mainApp) {
        this.mainApp = mainApp;
    }

    

    @Override
    protected void setupTableColumns() {
        org.masjidku.util.AlertHelper.setupInflowColumns(donatur, jumlah, tanggal);
}

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @FXML
    public void addListener() {
        TpaMasuk temp = new TpaMasuk();
        mainApp.editDonaturTpa(temp);
    }

    

    

    

    @FXML
    public void gotoHome() {
        mainApp.showTpa();
    }



    @Override protected org.slf4j.Logger getLogger() { return log; }
    @Override protected TableView<TpaMasuk> getTableView() { return tableTpa; }
    @Override protected Button getBtnEdit() { return btnEdit; }
    @Override protected Button getBtnRemove() { return btnRemove; }
    @Override protected List<TpaMasuk> fetchAllData() throws java.sql.SQLException { return client.getAllTpaMasuk(); }
    @Override protected boolean checkIfExist(TpaMasuk item) throws java.sql.SQLException { return client.isTpaMasukExist(item.id()); }
    @Override protected void deleteItem(TpaMasuk item) throws java.sql.SQLException { client.delete(item); }
    @Override protected void handleEdit(TpaMasuk item) { mainApp.editDonaturTpa(item); }

    @FXML public void onEditListener() { super.onEditAction(); }
}


