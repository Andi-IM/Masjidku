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

package org.masjidku.principal.report.kegiatan;

import org.masjidku.util.TableHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.masjidku.util.ServiceProvider;
import javafx.fxml.FXML;

import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import org.masjidku.MainApp;
import org.masjidku.events.client.model.TamuKegiatan;
import org.masjidku.events.client.service.TamuKegiatanService;

public class ListUndangan extends org.masjidku.accountant.BaseTableController<TamuKegiatan> {
    private static final Logger log = LoggerFactory.getLogger(ListUndangan.class);

    @FXML
    public TableView<TamuKegiatan> tblUndangan;
    @FXML
    public TableColumn<String, String> colNomor;
    @FXML
    public TableColumn<TamuKegiatan, String> colNama;
    @FXML

    public TableColumn<TamuKegiatan, String> colAlamat;
    @FXML
    public TableColumn<TamuKegiatan, String> colKeterangan;
    @FXML
    public TableColumn<TamuKegiatan, String> colKegiatan;
    @FXML
    public TableColumn<TamuKegiatan, String> colNotelp;
    @FXML
    public TableColumn<TamuKegiatan, String> colOperator;

    private MainApp mainApp;
    private final TamuKegiatanService dao;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public ListUndangan() {
        dao = ServiceProvider.get(TamuKegiatanService.class);
    }


    @Override
    protected void setupTableColumns() {
        TableHelper.setupUndanganColumns(colNama, colAlamat, colKeterangan, colKegiatan, colNotelp, colOperator);
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
        mainApp.showKegiatanOverview();
    }

    @Override
    protected org.slf4j.Logger getLogger() {
        return log;
    }

    @Override
    protected TableView<TamuKegiatan> getTableView() {
        return tblUndangan;
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
    protected List<TamuKegiatan> fetchAllData() throws java.sql.SQLException {
        return dao.getAll();
    }

    @Override
    protected boolean checkIfExist(TamuKegiatan item) {
        return false;
    }

    @Override
    protected void deleteItem(TamuKegiatan item) {
    }

    @Override
    protected void handleEdit(TamuKegiatan item) {
    }
}


