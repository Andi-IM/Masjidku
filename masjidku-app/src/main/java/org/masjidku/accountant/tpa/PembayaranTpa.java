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
import org.masjidku.MainApp;
import org.masjidku.accounting.client.model.tpa.TpaKeluar;
import org.masjidku.accounting.client.service.TpaKeluarService;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PembayaranTpa extends org.masjidku.accountant.BaseTableController<TpaKeluar> {
    private static final Logger log = LoggerFactory.getLogger(PembayaranTpa.class);
    private final TpaKeluarService dao = ServiceProvider.get(TpaKeluarService.class);

    @FXML
    private TableView<TpaKeluar> tableTpa;
    @FXML
    private TableColumn<TpaKeluar, String> nama;
    @FXML
    private TableColumn<TpaKeluar, String> jumlah;
    @FXML
    private TableColumn<TpaKeluar, String> tanggal;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnRemove;


    private MainApp mainApp;


    public void setMainApp(MainApp mainApp) {
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
        TpaKeluar temp = new TpaKeluar();
        mainApp.editAlokasiTpa(temp);
    }


    @FXML
    public void gotoHome() {
        mainApp.showTpa();
    }


    @Override
    protected org.slf4j.Logger getLogger() {
        return log;
    }

    @Override
    protected TableView<TpaKeluar> getTableView() {
        return tableTpa;
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
    protected List<TpaKeluar> fetchAllData() throws java.sql.SQLException {
        return dao.getAll();
    }

    @Override
    protected boolean checkIfExist(TpaKeluar item) throws java.sql.SQLException {
        return dao.isDataExist(item.getId());
    }

    @Override
    protected void deleteItem(TpaKeluar item) throws java.sql.SQLException {
        dao.delete(item.getId());
    }

    @Override
    protected void handleEdit(TpaKeluar item) {
        mainApp.editAlokasiTpa(item);
    }

    @FXML
    public void onEditListener() {
        super.onEditAction();
    }
}

