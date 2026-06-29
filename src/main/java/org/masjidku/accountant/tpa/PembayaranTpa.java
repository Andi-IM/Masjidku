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

package org.masjidku.accountant.tpa;

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
import org.masjidku.model.accounting.tpa.TpaKeluar;
import org.masjidku.model.accounting.tpa.TpaKeluarDao;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PembayaranTpa implements Initializable {

    @FXML
    private TableView<TpaKeluar> tableTpa;
    @FXML
    private TableColumn<TpaKeluar, String> nama;
    @FXML
    private TableColumn<TpaKeluar, String> jumlah;
    @FXML
    private TableColumn<TpaKeluar, String> tanggal;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnRemove;

    /**
     * The data as an observable list of Anak Yatim.
     */
    private final ObservableList<TpaKeluar> dataTpa =
            FXCollections.observableArrayList();

    private MainApp mainApp;

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * get User Data from DAO.
     *
     * @return Observable List
     */
    private ObservableList<TpaKeluar> getDataTpa() {
        TpaKeluarDao dao = new TpaKeluarDao();
        if (dao.getConnection()) {
            try {
                dataTpa.addAll(dao.getAll());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dataTpa;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableTpa.setItems(getDataTpa());

        nama.setCellValueFactory(new PropertyValueFactory<>("tujuan"));
        jumlah.setCellValueFactory(new PropertyValueFactory<>("jumlah"));
        tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
    }

    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }

    @FXML
    public void onMouseClicked() {
        if(tableTpa.getSelectionModel().isEmpty()){
            btnEdit.setDisable(true);
            btnRemove.setDisable(true);
        } else {
            btnEdit.setDisable(false);
            btnRemove.setDisable(false);
        }
    }

    @FXML
    public void addListener() {
        TpaKeluar temp = new TpaKeluar();
        mainApp.editAlokasiTpa(temp);
    }

    @FXML
    public void editListener() {
        TpaKeluar selectedItem = tableTpa.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            mainApp.editAlokasiTpa(selectedItem);
        } else {
            alertError("Null Error", "Data tidak ditemukan!");
        }
    }

    @FXML
    public void onRemoveListener() {
        TpaKeluar selectedItem = tableTpa.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            TpaKeluarDao dao = new TpaKeluarDao();
            if (dao.getConnection()) {
                try {
                    if (dao.isDataExist(selectedItem.getId())) {
                        tableTpa.getItems().remove(selectedItem);
                        dao.delete(selectedItem.getId());
                        alertInfo("Success", "User dihapus!");
                    } else {
                        alertError("SQL Error", "User tidak ditemukan!");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                alertError("Offline", "Database tidak terhubung!");
            }
        }
    }

    @FXML
    public void gotoHome() { mainApp.showTpa(); }

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
