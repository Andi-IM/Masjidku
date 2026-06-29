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

package org.masjidku.accountant.pembangunan;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.masjidku.navigation.AppRouter;
import org.masjidku.accounting.client.model.pembangunan.Pembangunan;
import org.masjidku.accounting.client.service.PembangunanService;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PembayaranPembangunan extends org.masjidku.accountant.BaseTableController<Pembangunan> {
    private static final Logger log = LoggerFactory.getLogger(PembayaranPembangunan.class);
    private final PembangunanService dao = ServiceProvider.get(PembangunanService.class);

    @FXML
    private TableView<Pembangunan> tablePembangunan;
    @FXML
    private TableColumn<Pembangunan, String> nama;
    @FXML
    private TableColumn<Pembangunan, String> keterangan;
    @FXML
    private TableColumn<Pembangunan, String> jumlah;
    @FXML
    private TableColumn<Pembangunan, String> tanggal;
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
keterangan.setCellValueFactory(new PropertyValueFactory<>("keterangan"));
}

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }


    @FXML
    public void addListener() {
        Pembangunan temp = new Pembangunan();
        mainApp.editAlokasiPembangunan(temp);
    }


    @FXML
    public void gotoHome() {
        mainApp.showPembangunan();
    }


    @Override
    protected org.slf4j.Logger getLogger() {
        return log;
    }

    @Override
    protected TableView<Pembangunan> getTableView() {
        return tablePembangunan;
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
    protected List<Pembangunan> fetchAllData() throws java.sql.SQLException {
        return dao.getAll();
    }

    @Override
    protected boolean checkIfExist(Pembangunan item) throws java.sql.SQLException {
        return dao.isDataExist(item.getId());
    }

    @Override
    protected void deleteItem(Pembangunan item) throws java.sql.SQLException {
        dao.delete(item.getId());
    }

    @Override
    protected void handleEdit(Pembangunan item) {
        mainApp.editAlokasiPembangunan(item);
    }

    @FXML
    public void onEditListener() {
        super.onEditAction();
    }
}


