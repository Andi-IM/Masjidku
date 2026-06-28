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

import org.masjidku.util.ServiceProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.masjidku.MainApp;
import org.masjidku.events.client.model.TamuKegiatan;
import org.masjidku.events.client.service.TamuKegiatanService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SecretaryUndangan implements Initializable {
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
    private final TamuKegiatanService dao;

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;

    public SecretaryUndangan() { dao = ServiceProvider.get(TamuKegiatanService.class); }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private final ObservableList<TamuKegiatan> undanganData =
            FXCollections.observableArrayList();

    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblUndangan.setItems(org.masjidku.util.AlertHelper.loadTableData(undanganData, dao::getAll, log));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        colKeterangan.setCellValueFactory(new PropertyValueFactory<>("keterangan"));
        colKegiatan.setCellValueFactory(new PropertyValueFactory<>("kegiatan"));
        colNotelp.setCellValueFactory(new PropertyValueFactory<>("notelp"));
    }

    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }

    @FXML
    public void editListener() {
        TamuKegiatan selectedItem = tblUndangan.getSelectionModel().getSelectedItem();
        if (selectedItem != null){
            mainApp.showUndanganEditForm(selectedItem);
        } else {
            org.masjidku.util.AlertHelper.alertError(dialogStage, "Null Error", "Kegiatan tidak ditemukan!");
        }
    }

    @FXML
    public void onRemoveListener() {
        TamuKegiatan selectedUndangan = tblUndangan.getSelectionModel().getSelectedItem();
        if (selectedUndangan != null){
                try {
                    if (dao.isUndanganExist(null)){
                        tblUndangan.getItems().remove(selectedUndangan);
                        dao.delete(null);
                        org.masjidku.util.AlertHelper.alertInfo(dialogStage, "Success", "Kegiatan Dihapus!");
                    } else {
                        org.masjidku.util.AlertHelper.alertError(dialogStage, "SQL Error", "Kegiatan tidak ditemukan!");
                    }
                } catch (SQLException e) {
                    log.error("An error occurred", e);
                }
            
        }
    }

    @FXML
    public void onMouseClicked() { org.masjidku.util.AlertHelper.handleTableSelection(tblUndangan, btnEdit, btnRemove); }

    @FXML
    public void tamuListener() {
        TamuKegiatan temp = new TamuKegiatan();
        mainApp.showUndanganEditForm(temp);
    }

    

    
}
