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

package org.masjidku.secretary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;

import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.masjidku.MainApp;
import org.masjidku.events.client.model.Tamu;
import org.masjidku.events.client.service.TamuService;

public class SecretaryTamu extends org.masjidku.accountant.BaseTableController<Tamu> {
    private static final Logger log = LoggerFactory.getLogger(SecretaryTamu.class);
    @FXML
    public Button btnEdit;
    @FXML
    public Button btnRemove;
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

    private MainApp mainApp;
    final TamuService dao = org.masjidku.util.ServiceProvider.get(TamuService.class);

    

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    

    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }

    @FXML
    public void addListener(){
        Tamu temp = new Tamu();
        mainApp.showTamuEditForm(temp);
    }

    

    

    /**
     * Remove the selected kegiatan.
     */
    

    

    

    

    @Override
    protected void setupTableColumns() {

        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        colNotelp.setCellValueFactory(new PropertyValueFactory<>("notelp"));
    }

    @Override protected org.slf4j.Logger getLogger() { return log; }
    @Override protected TableView<Tamu> getTableView() { return tblTamu; }
    @Override protected Button getBtnEdit() { return btnEdit; }
    @Override protected Button getBtnRemove() { return btnRemove; }
    @Override protected List<Tamu> fetchAllData() throws java.sql.SQLException { return dao.getAll(); }
    @Override protected boolean checkIfExist(Tamu item) throws java.sql.SQLException { return dao.isTamuExist(item.getIdTamu()); }
    @Override protected void deleteItem(Tamu item) throws java.sql.SQLException { dao.delete(item.getIdTamu()); }
    @Override protected void handleEdit(Tamu item) {  }

    @FXML public void onEditListener() { super.onEditAction(); }
}

