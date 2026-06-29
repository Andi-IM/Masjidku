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
import org.masjidku.model.kegiatan.TamuKegiatan;
import org.masjidku.model.kegiatan.TamuKegiatanDao;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SecretaryUndangan implements Initializable {

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
    private final TamuKegiatanDao dao;

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;

    public SecretaryUndangan() { dao = new TamuKegiatanDao(); }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private final ObservableList<TamuKegiatan> undanganData =
            FXCollections.observableArrayList();

    private ObservableList<TamuKegiatan> getUndanganData(){
        if (dao.getConnection()){
            try {
                undanganData.addAll(dao.getAll());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return undanganData;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblUndangan.setItems(getUndanganData());
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
            alertError("Null Error", "Kegiatan tidak ditemukan!");
        }
    }

    @FXML
    public void onRemoveListener() {
        TamuKegiatan selectedUndangan = tblUndangan.getSelectionModel().getSelectedItem();
        if (selectedUndangan != null){
            if (dao.getConnection()){
                try {
                    if (dao.isUndanganExist(null)){
                        tblUndangan.getItems().remove(selectedUndangan);
                        dao.delete(null);
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
        if(tblUndangan.getSelectionModel().isEmpty()){
            btnEdit.setDisable(true);
            btnRemove.setDisable(true);
        } else {
            btnEdit.setDisable(false);
            btnRemove.setDisable(false);
        }
    }

    @FXML
    public void tamuListener() {
        TamuKegiatan temp = new TamuKegiatan();
        mainApp.showUndanganEditForm(temp);
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
}
