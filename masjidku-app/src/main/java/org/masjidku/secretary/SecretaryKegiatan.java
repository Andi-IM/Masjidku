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
import org.masjidku.events.client.model.Kegiatan;
import org.masjidku.events.client.service.KegiatanService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SecretaryKegiatan implements Initializable {
    private static final Logger log = LoggerFactory.getLogger(SecretaryKegiatan.class);

    @FXML
    public Button btnEdit;
    @FXML
    public Button btnRemove;
    @FXML
    public TableView<Kegiatan> tblKegiatan;
    @FXML
    public TableColumn<Kegiatan, String> colNomor;
    @FXML
    public TableColumn<Kegiatan, String> colNmKegiatan;
    @FXML
    public TableColumn<Kegiatan, String> colTempatKegiatan;
    @FXML
    public TableColumn<Kegiatan, String> colWaktuKegiatan;
    @FXML
    public TableColumn<Kegiatan, String> colTanggalKegiatan;

    private MainApp mainApp;
    final KegiatanService dao;

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * The Constructor
     * The Constructor is called before the initialize() method.
     */
    public SecretaryKegiatan() { dao = ServiceProvider.get(KegiatanService.class); }

    /**
     * The data as an observable list of Users.
     */
    private final ObservableList<Kegiatan> kegiatanData =
            FXCollections.observableArrayList();

    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }

    @FXML
    public void addListener(){
        Kegiatan temp = new Kegiatan();
        mainApp.showKegiatanEditform(temp);
    }

    

    @FXML
    public void editListener() {
        Kegiatan selectedKegiatan = tblKegiatan.getSelectionModel().getSelectedItem();
        if (selectedKegiatan != null){
            mainApp.showKegiatanEditform(selectedKegiatan);
        } else {
            org.masjidku.util.AlertHelper.alertError(dialogStage, "Null Error", "Kegiatan tidak ditemukan!");
        }
    }

    /**
     * Remove the selected kegiatan.
     */
    @FXML
    public void onRemoveListener() {
        Kegiatan selectedKegiatan = tblKegiatan.getSelectionModel().getSelectedItem();
        if (selectedKegiatan != null){
                try {
                    if (dao.isKegiatanExist(selectedKegiatan.getIdKegiatan())){
                        tblKegiatan.getItems().remove(selectedKegiatan);
                        dao.delete(selectedKegiatan.getIdKegiatan());
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
    public void onMouseClicked() { org.masjidku.util.AlertHelper.handleTableSelection(tblKegiatan, btnEdit, btnRemove); }

    

    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tblKegiatan.setItems(org.masjidku.util.AlertHelper.loadTableData(kegiatanData, dao::getAll, log));
        colNomor.setCellValueFactory(new PropertyValueFactory<>(""));
        colNmKegiatan.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colTempatKegiatan.setCellValueFactory(new PropertyValueFactory<>("tempat"));
        colWaktuKegiatan.setCellValueFactory(new PropertyValueFactory<>("waktu"));
        colTanggalKegiatan.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
    }

    @FXML
    public void tamuListener() {
        mainApp.showUndangan();
    }
}
