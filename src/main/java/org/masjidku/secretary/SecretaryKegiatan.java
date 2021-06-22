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
import org.masjidku.model.kegiatan.Kegiatan;
import org.masjidku.model.kegiatan.KegiatanDao;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SecretaryKegiatan implements Initializable {

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
    KegiatanDao dao;

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
    public SecretaryKegiatan() { dao = new KegiatanDao(); }

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

    private ObservableList<Kegiatan> getKegiatanData() {
        if (dao.getConnection()){
            try {
                kegiatanData.addAll(dao.getAll());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return kegiatanData;
    }

    @FXML
    public void editListener() {
        Kegiatan selectedKegiatan = tblKegiatan.getSelectionModel().getSelectedItem();
        if (selectedKegiatan != null){
            mainApp.showKegiatanEditform(selectedKegiatan);
        } else {
            alertError("Null Error", "Kegiatan tidak ditemukan!");
        }
    }

    /**
     * Remove the selected kegiatan.
     */
    @FXML
    public void onRemoveListener() {
        Kegiatan selectedKegiatan = tblKegiatan.getSelectionModel().getSelectedItem();
        if (selectedKegiatan != null){
            if (dao.getConnection()){
                try {
                    if (dao.isKegiatanExist(selectedKegiatan.getIdKegiatan())){
                        tblKegiatan.getItems().remove(selectedKegiatan);
                        dao.delete(selectedKegiatan.getIdKegiatan());
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
        if(tblKegiatan.getSelectionModel().isEmpty()){
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

        tblKegiatan.setItems(getKegiatanData());
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
