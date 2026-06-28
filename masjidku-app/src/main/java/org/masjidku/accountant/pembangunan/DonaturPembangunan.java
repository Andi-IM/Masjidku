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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.masjidku.MainApp;
import org.masjidku.accounting.client.model.pembangunan.DonasiPembangunan;
import org.masjidku.accounting.client.service.DonasiPembangunanService;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DonaturPembangunan implements Initializable {
    private static final Logger log = LoggerFactory.getLogger(DonaturPembangunan.class);
    private final DonasiPembangunanService dao = ServiceProvider.get(DonasiPembangunanService.class);

    @FXML
    private TableView<DonasiPembangunan> tablePembangunan;
    @FXML
    private TableColumn<DonasiPembangunan, String> donatur;
    @FXML
    private TableColumn<DonasiPembangunan, String> jumlah;
    @FXML
    private TableColumn<DonasiPembangunan, String> tanggal;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnRemove;

    private MainApp mainApp;

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;

    /**
     * The data as an observable list of Donatur.
     */
    private final ObservableList<DonasiPembangunan> donaturData =
            FXCollections.observableArrayList();


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * get AnakYatim Data from DAO.
     *
     * @return Observable List
     */
    private ObservableList<DonasiPembangunan> getDonaturData() {
        try {
            donaturData.addAll(dao.getAll());
        } catch (SQLException e) {
            log.error("An error occurred", e);
        }
        return donaturData;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tablePembangunan.setItems(getDonaturData());

        donatur.setCellValueFactory(new PropertyValueFactory<>("donatur"));
        jumlah.setCellValueFactory(new PropertyValueFactory<>("jumlah"));
        tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @FXML
    public void onMouseClicked() { org.masjidku.util.AlertHelper.handleTableSelection(tablePembangunan, btnEdit, btnRemove); }

    @FXML
    public void addListener() {
        DonasiPembangunan temp = new DonasiPembangunan();
        mainApp.editDonaturPembangunan(temp);
    }

    @FXML
    public void editListener() {
        DonasiPembangunan selectedItem = tablePembangunan.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            mainApp.editDonaturPembangunan(selectedItem);
        } else {
            org.masjidku.util.AlertHelper.alertError(dialogStage, "Null Error", "Data tidak ditemukan!");
        }
    }

    @FXML
    public void onRemoveListener() {
        DonasiPembangunan selectedItem = tablePembangunan.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            try {
                if (dao.isDonaturExist(selectedItem.getId())) {
                    tablePembangunan.getItems().remove(selectedItem);
                    dao.delete(selectedItem.getId());
                    org.masjidku.util.AlertHelper.alertInfo(dialogStage, "Success", "User dihapus!");
                } else {
                    org.masjidku.util.AlertHelper.alertError(dialogStage, "SQL Error", "User tidak ditemukan!");
                }
            } catch (SQLException e) {
                log.error("An error occurred", e);
            }
        }
    }

    @FXML
    public void gotoHome() {
        mainApp.showPembangunan();
    }


}
