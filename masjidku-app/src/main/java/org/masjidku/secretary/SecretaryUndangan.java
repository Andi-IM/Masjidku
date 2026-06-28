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

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.masjidku.MainApp;
import org.masjidku.events.client.model.TamuKegiatan;
import org.masjidku.events.client.service.TamuKegiatanService;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SecretaryUndangan extends org.masjidku.accountant.BaseTableController<TamuKegiatan> {
    private static final Logger log = LoggerFactory.getLogger(SecretaryUndangan.class);

    @FXML
    public Button btnEdit;
    @FXML
    public Button btnRemove;
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

    private MainApp mainApp;
    private    final TamuKegiatanService dao = org.masjidku.util.ServiceProvider.get(TamuKegiatanService.class);

    

    public SecretaryUndangan() { }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    

    

    @Override
    protected void setupTableColumns() {
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        colKeterangan.setCellValueFactory(new PropertyValueFactory<>("keterangan"));
        colKegiatan.setCellValueFactory(new PropertyValueFactory<>("kegiatan"));
        colNotelp.setCellValueFactory(new PropertyValueFactory<>("notelp"));
    }

    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }

    

    

    

    @FXML
    public void tamuListener() {
        TamuKegiatan temp = new TamuKegiatan();
        mainApp.showUndanganEditForm(temp);
    }

    

    

    @Override protected org.slf4j.Logger getLogger() { return log; }
    @Override protected TableView<TamuKegiatan> getTableView() { return tblUndangan; }
    @Override protected Button getBtnEdit() { return btnEdit; }
    @Override protected Button getBtnRemove() { return btnRemove; }
    @Override protected List<TamuKegiatan> fetchAllData() throws java.sql.SQLException { return dao.getAll(); }
    @Override protected boolean checkIfExist(TamuKegiatan item) throws java.sql.SQLException { return dao.isUndanganExist(item.getIdUndangan()); }
    @Override protected void deleteItem(TamuKegiatan item) throws java.sql.SQLException { dao.delete(item.getIdUndangan()); }
    @Override protected void handleEdit(TamuKegiatan item) {  }

    @FXML public void onEditListener() { super.onEditAction(); }
}

