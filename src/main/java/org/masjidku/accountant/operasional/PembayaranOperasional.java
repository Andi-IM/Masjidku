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

package org.masjidku.accountant.operasional;

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
import org.masjidku.model.accounting.operasional.Operasional;
import org.masjidku.model.accounting.operasional.OperationalDao;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PembayaranOperasional implements Initializable {

    @FXML
    private TableView<Operasional> tableOperasional;
    @FXML
    private TableColumn<Operasional, String> nama;
    @FXML
    private TableColumn<Operasional, String> keterangan;
    @FXML
    private TableColumn<Operasional, String> jumlah;
    @FXML
    private TableColumn<Operasional, String> tanggal;
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
    private final ObservableList<Operasional> dataOperasional =
            FXCollections.observableArrayList();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * get User Data from DAO.
     *
     * @return Observable List
     */
    private ObservableList<Operasional> getDataOperasional() {
        OperationalDao dao = new OperationalDao();
        if (dao.getConnection()) {
            try {
                dataOperasional.addAll(dao.getAll());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dataOperasional;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableOperasional.setItems(getDataOperasional());

        nama.setCellValueFactory(new PropertyValueFactory<>("tujuan"));
        keterangan.setCellValueFactory(new PropertyValueFactory<>("keterangan"));
        jumlah.setCellValueFactory(new PropertyValueFactory<>("jumlah"));
        tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
    }

    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }

    @FXML
    public void onMouseClicked() {
        if(tableOperasional.getSelectionModel().isEmpty()){
            btnEdit.setDisable(true);
            btnRemove.setDisable(true);
        } else {
            btnEdit.setDisable(false);
            btnRemove.setDisable(false);
        }
    }

    @FXML
    public void addListener() {
        Operasional temp = new Operasional();
        mainApp.editAlokasiOperasional(temp);
    }

    @FXML
    public void editListener() {
        Operasional selectedItem = tableOperasional.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            mainApp.editAlokasiOperasional(selectedItem);
        } else {
            alertError("Null Error", "Data tidak ditemukan!");
        }
    }

    @FXML
    public void onRemoveListener() {
        Operasional selectedItem = tableOperasional.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            OperationalDao dao = new OperationalDao();
            if (dao.getConnection()) {
                try {
                    if (dao.isDataExist(selectedItem.getId())) {
                        tableOperasional.getItems().remove(selectedItem);
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
    public void gotoHome() {
        mainApp.showOperasional();
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
