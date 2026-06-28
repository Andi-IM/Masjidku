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
import org.masjidku.events.client.model.Tamu;
import org.masjidku.events.client.service.TamuService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SecretaryTamu implements Initializable {
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
    final TamuService dao;

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
    public SecretaryTamu() { dao = ServiceProvider.get(TamuService.class); }

    /**
     * The data as an observable list of Users.
     */
    private final ObservableList<Tamu> tamuData =
            FXCollections.observableArrayList();

    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }

    @FXML
    public void addListener(){
        Tamu temp = new Tamu();
        mainApp.showTamuEditForm(temp);
    }

    private ObservableList<Tamu> getTamuData() {
                try {
                    tamuData.addAll(dao.getAll());
                } catch (SQLException e) {
                    log.error("An error occurred", e);
                }
            
            return tamuData;
    }

    @FXML
    public void editListener() {
        Tamu selectedTamu = tblTamu.getSelectionModel().getSelectedItem();
        if (selectedTamu != null){
            mainApp.showTamuEditForm(selectedTamu);
        } else {
            org.masjidku.util.AlertHelper.alertError(dialogStage, "Null Error", "Kegiatan tidak ditemukan!");
        }
    }

    /**
     * Remove the selected kegiatan.
     */
    @FXML
    public void onRemoveListener() {
        Tamu selectedTamu = tblTamu.getSelectionModel().getSelectedItem();
        if (selectedTamu != null){
                try {
                    if (dao.isTamuExist(selectedTamu.getIdTamu())){
                        tblTamu.getItems().remove(selectedTamu);
                        dao.delete(selectedTamu.getIdTamu());
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
    public void onMouseClicked() { org.masjidku.util.AlertHelper.handleTableSelection(tblTamu, btnEdit, btnRemove); }

    

    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tblTamu.setItems(getTamuData());
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        colNotelp.setCellValueFactory(new PropertyValueFactory<>("notelp"));
    }
}
