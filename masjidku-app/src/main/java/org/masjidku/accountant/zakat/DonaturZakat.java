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

package org.masjidku.accountant.zakat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.masjidku.controller.BaseTableController;
import org.masjidku.navigation.AppRouter;
import org.masjidku.accounting.client.model.zakat.ZakatMasuk;


import org.masjidku.accounting.client.service.AccountingClient;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DonaturZakat extends BaseTableController<ZakatMasuk> {
    private static final Logger log = LoggerFactory.getLogger(DonaturZakat.class);
    private final AccountingClient client = ServiceProvider.get(AccountingClient.class);

    @FXML
    private TableView<ZakatMasuk> zakatTable;
    @FXML
    private TableColumn<ZakatMasuk, String> donatur;
    @FXML
    private TableColumn<ZakatMasuk, String> jumlah;
    @FXML
    private TableColumn<ZakatMasuk, String> tanggal;
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
        ZakatMasuk temp = new ZakatMasuk();
        mainApp.editDonaturZakat(temp);
    }


    @FXML
    public void gotoHome() {
        mainApp.showZakat();
    }


    @Override
    protected org.slf4j.Logger getLogger() {
        return log;
    }

    @Override
    protected TableView<ZakatMasuk> getTableView() {
        return zakatTable;
    }

    @Override
    protected Button getBtnEdit() {
        return btnEdit;
    }

    @Override
    protected Button getBtnRemove() {
        return btnRemove;
    }

    @Override
    protected List<ZakatMasuk> fetchAllData() throws java.sql.SQLException {
        return client.getAllZakatMasuk();
    }

    @Override
    protected boolean checkIfExist(ZakatMasuk item) throws java.sql.SQLException {
        return client.isZakatMasukExist(item.id());
    }

    @Override
    protected void deleteItem(ZakatMasuk item) throws java.sql.SQLException {
        client.delete(item);
    }

    @Override
    protected void handleEdit(ZakatMasuk item) {
        mainApp.editDonaturZakat(item);
    }

    @FXML
    public void onEditListener() {
        super.onEditAction();
    }
}


