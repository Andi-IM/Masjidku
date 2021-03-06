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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.masjidku.MainApp;
import org.masjidku.model.kegiatan.Tamu;
import org.masjidku.model.kegiatan.TamuDao;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SecretaryTamu implements Initializable {
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
    TamuDao dao;

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
    public SecretaryTamu() { dao = new TamuDao(); }

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
            if (dao.getConnection()){
                try {
                    tamuData.addAll(dao.getAll());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return tamuData;
    }

    @FXML
    public void editListener() {
        Tamu selectedTamu = tblTamu.getSelectionModel().getSelectedItem();
        if (selectedTamu != null){
            mainApp.showTamuEditForm(selectedTamu);
        } else {
            alertError("Null Error", "Kegiatan tidak ditemukan!");
        }
    }

    /**
     * Remove the selected kegiatan.
     */
    @FXML
    public void onRemoveListener() {
        Tamu selectedTamu = tblTamu.getSelectionModel().getSelectedItem();
        if (selectedTamu != null){
            if (dao.getConnection()){
                try {
                    if (dao.isTamuExist(selectedTamu.getIdTamu())){
                        tblTamu.getItems().remove(selectedTamu);
                        dao.delete(selectedTamu.getIdTamu());
                        alertInfo("Success", "Kegiatan Dihapus!");
                    } else {
                        alertError("SQL Error", "Kegiatan tidak ditemukan!");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                alertError("Offline", "Database tidak terhuhung!");
            }
        }
    }

    @FXML
    public void onMouseClicked() {
        if(tblTamu.getSelectionModel().isEmpty()){
            btnEdit.setDisable(true);
            btnRemove.setDisable(true);
        } else {
            btnEdit.setDisable(false);
            btnRemove.setDisable(false);
        }
    }

    /**
     * Alert Error Builder
     * @param header header message
     * @param content content message
     */
    @SuppressWarnings("SameParameterValue")
    private void alertError(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Prompt");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }

    /**
     * Alert Info Builder
     * @param header header message
     * @param content content message
     */
    @SuppressWarnings("SameParameterValue")
    private void alertInfo(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(dialogStage);
        alert.setTitle("Prompt");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tblTamu.setItems(getTamuData());
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        colNotelp.setCellValueFactory(new PropertyValueFactory<>("notelp"));
    }
}
