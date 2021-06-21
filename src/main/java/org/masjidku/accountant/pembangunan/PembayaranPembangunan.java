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

package org.masjidku.accountant.pembangunan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.masjidku.MainApp;
import org.masjidku.model.accounting.operasional.Operasional;
import org.masjidku.model.accounting.operasional.OperationalDao;
import org.masjidku.model.accounting.pembangunan.Pembangunan;
import org.masjidku.model.accounting.pembangunan.PembangunanDao;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PembayaranPembangunan implements Initializable {

    @FXML
    private TableView<Pembangunan> tablePembangunan;
    @FXML
    private TableColumn<Pembangunan, String> nama;
    @FXML
    private TableColumn<Pembangunan, String> keterangan;
    @FXML
    private TableColumn<Pembangunan, String> jumlah;
    @FXML
    private TableColumn<Pembangunan, String> tanggal;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnRemove;

    private MainApp mainApp;

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;

    /**
     * The data as an observable list of Data Operasional.
     */
    private final ObservableList<Pembangunan> dataPembangunan =
            FXCollections.observableArrayList();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * get User Data from DAO.
     *
     * @return Observable List
     */
    private ObservableList<Pembangunan> getDataPembangunan() {
        PembangunanDao dao = new PembangunanDao();
        if (dao.getConnection()) {
            try {
                dataPembangunan.addAll(dao.getAll());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dataPembangunan;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tablePembangunan.setItems(getDataPembangunan());

        nama.setCellValueFactory(new PropertyValueFactory<>("tujuan"));
        keterangan.setCellValueFactory(new PropertyValueFactory<>("keterangan"));
        jumlah.setCellValueFactory(new PropertyValueFactory<>("jumlah"));
        tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @FXML
    public void onMouseClicked() {
        if(tablePembangunan.getSelectionModel().isEmpty()){
            btnEdit.setDisable(true);
            btnRemove.setDisable(true);
        } else {
            btnEdit.setDisable(false);
            btnRemove.setDisable(false);
        }
    }

    @FXML
    public void addListener() {
        Pembangunan temp = new Pembangunan();
        mainApp.editAlokasiPembangunan(temp);
    }

    @FXML
    public void editListener() {
        Pembangunan selectedItem = tablePembangunan.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            mainApp.editAlokasiPembangunan(selectedItem);
        } else {
            alertError("Null Error", "Data tidak ditemukan!");
        }
    }

    @FXML
    public void onRemoveListener(ActionEvent actionEvent) {
        Pembangunan selectedItem = tablePembangunan.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            PembangunanDao dao = new PembangunanDao();
            if (dao.getConnection()) {
                try {
                    if (dao.isDataExist(selectedItem.getId())) {
                        tablePembangunan.getItems().remove(selectedItem);
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
    public void gotoHome() { mainApp.showPembangunan(); }

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
