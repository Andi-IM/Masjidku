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

package org.masjidku.accountant.operasional;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.masjidku.navigation.AppRouter;
import org.masjidku.accounting.client.model.operasional.Operasional;
import org.masjidku.accounting.client.service.OperationalService;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PembayaranOperasional extends org.masjidku.accountant.BaseTableController<Operasional> {
    private static final Logger log = LoggerFactory.getLogger(PembayaranOperasional.class);
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
        Operasional temp = new Operasional();
        mainApp.editAlokasiOperasional(temp);
    }


    @FXML
    public void gotoHome() {
        mainApp.showOperasional();
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
        return btnEdit;
    }

    @Override
    protected Button getBtnRemove() {
        return btnRemove;
    }

    @Override
    protected List<Operasional> fetchAllData() throws java.sql.SQLException {
        return dao.getAll();
    }

    @Override
    protected boolean checkIfExist(Operasional item) throws java.sql.SQLException {
        return dao.isDataExist(item.getId());
    }

    @Override
    protected void deleteItem(Operasional item) throws java.sql.SQLException {
        dao.delete(item.getId());
    }

    @Override
    protected void handleEdit(Operasional item) {
        mainApp.editAlokasiOperasional(item);
    }

    @FXML
    public void onEditListener() {
        super.onEditAction();
    }
}


