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

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.masjidku.MainApp;
import org.masjidku.events.client.model.Tamu;
import org.masjidku.events.client.service.TamuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ListTamu extends org.masjidku.accountant.BaseTableController<Tamu> {
    private static final Logger log = LoggerFactory.getLogger(ListTamu.class);
    @FXML
    public TableView<Tamu> tblTamu;
    @FXML
    public TableColumn<Tamu, String> colNama;
    @FXML
    public TableColumn<Tamu, String> colAlamat;
    @FXML
    public TableColumn<Tamu, String> colNotelp;
    @FXML
    public TableColumn<Tamu, String> colNomor;
    @FXML
    public TableColumn<Tamu, String> colOperator;

    private MainApp mainApp;
    private final TamuService dao = org.masjidku.util.ServiceProvider.get(TamuService.class);

    

    @Override 
    protected void setupTableColumns() {
        if (colNama != null) colNama.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("nama"));
        if (colAlamat != null) colAlamat.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("alamat"));
        if (colNotelp != null) colNotelp.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("notelp"));
        if (colOperator != null) colOperator.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("operator"));
    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    

    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }

    @FXML
    public void showReport() {
    }

    @FXML
    public void gotoHome() { mainApp.showKegiatanOverview(); }

    @Override protected org.slf4j.Logger getLogger() { return log; }
    @Override protected TableView<Tamu> getTableView() { return tblTamu; }
    @Override protected Button getBtnEdit() { return null; }
    @Override protected Button getBtnRemove() { return null; }
    @Override protected List<Tamu> fetchAllData() throws java.sql.SQLException { return dao.getAll(); }
    @Override protected boolean checkIfExist(Tamu item) throws java.sql.SQLException { return false; }
    @Override protected void deleteItem(Tamu item) throws java.sql.SQLException {  }
    @Override protected void handleEdit(Tamu item) {  }
}



