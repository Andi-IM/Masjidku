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
import org.masjidku.accounting.client.model.zakat.ZakatKeluar;


import org.masjidku.accounting.client.service.AccountingClient;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PenerimaZakat extends BaseTableController<ZakatKeluar> {
    private static final Logger log = LoggerFactory.getLogger(PenerimaZakat.class);
    private final AccountingClient client = ServiceProvider.get(AccountingClient.class);

    @FXML
    private TableView<ZakatKeluar> tableZakat;
    @FXML
    private TableColumn<ZakatKeluar, String> nama;
    @FXML
    private TableColumn<ZakatKeluar, String> jumlah;
    @FXML
    private TableColumn<ZakatKeluar, String> tanggal;
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
        org.masjidku.util.AlertHelper.setupOutflowColumns(nama, jumlah, tanggal);
}

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }


    @FXML
    public void addListener() {
        ZakatKeluar temp = new ZakatKeluar();
        mainApp.editPenerimaZakat(temp);
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
    protected TableView<ZakatKeluar> getTableView() {
        return tableZakat;
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
    protected List<ZakatKeluar> fetchAllData() throws java.sql.SQLException {
        return client.getAllZakatKeluar();
    }

    @Override
    protected boolean checkIfExist(ZakatKeluar item) throws java.sql.SQLException {
        return client.isZakatKeluarExist(item.id());
    }

    @Override
    protected void deleteItem(ZakatKeluar item) throws java.sql.SQLException {
        client.delete(item);
    }

    @Override
    protected void handleEdit(ZakatKeluar item) {
        mainApp.editPenerimaZakat(item);
    }

    @FXML
    public void onEditListener() {
        super.onEditAction();
    }
}


