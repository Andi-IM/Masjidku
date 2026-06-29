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
import org.masjidku.navigation.AppRouter;
import org.masjidku.events.client.model.Kegiatan;
import org.masjidku.events.client.service.KegiatanService;

public class ListKegiatan extends org.masjidku.accountant.BaseTableController<Kegiatan> {
    private static final Logger log = LoggerFactory.getLogger(ListKegiatan.class);
    @FXML
    public TableView<Kegiatan> tblKegiatan;
    @FXML
    public TableColumn<Kegiatan, String> colNomor;
    @FXML
    public TableColumn<Kegiatan, String> colNmKegiatan;
    @FXML
    public TableColumn<Kegiatan, String> colTanggalKegiatan;
    @FXML
    public TableColumn<Kegiatan, String> colTempatKegiatan;
    @FXML
    public TableColumn<Kegiatan, String> colWaktuKegiatan;
    @FXML
    public TableColumn<Kegiatan, String> colOperator;
    public AppRouter mainApp;
    private final KegiatanService dao;

    public void setMainApp(AppRouter mainApp) {
        this.mainApp = mainApp;
    }

    public ListKegiatan() {
        dao = ServiceProvider.get(KegiatanService.class);
    }


    @Override
    protected void setupTableColumns() {
        TableHelper.setupKegiatanColumns(colNomor, colNmKegiatan, colTempatKegiatan, colWaktuKegiatan, colTanggalKegiatan, colOperator);
    }


    @FXML
    public void printReport() {
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
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
    protected TableView<Kegiatan> getTableView() {
        return tblKegiatan;
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
    protected List<Kegiatan> fetchAllData() throws java.sql.SQLException {
        return dao.getAll();
    }

    @Override
    protected boolean checkIfExist(Kegiatan item) {
        return false;
    }

    @Override
    protected void deleteItem(Kegiatan item) {
    }

    @Override
    protected void handleEdit(Kegiatan item) {
    }
}



