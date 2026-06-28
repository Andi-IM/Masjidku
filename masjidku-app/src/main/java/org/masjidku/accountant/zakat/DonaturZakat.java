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

package org.masjidku.accountant.zakat;

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
import org.masjidku.accounting.client.model.zakat.ZakatMasuk;
import org.masjidku.accounting.client.service.ZakatMasukService;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DonaturZakat implements Initializable {
    private static final Logger log = LoggerFactory.getLogger(DonaturZakat.class);
    private final ZakatMasukService dao = ServiceProvider.get(ZakatMasukService.class);

    @FXML
    private TableView<ZakatMasuk> zakatTable;
    @FXML
    private TableColumn<ZakatMasuk, String> donatur;
    @FXML
    private TableColumn<ZakatMasuk, String> jumlah;
    @FXML
    private TableColumn<ZakatMasuk, String> tanggal;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnRemove;

    private MainApp mainApp;

    /**
     * The data as an observable list of Users.
     */
    private final ObservableList<ZakatMasuk> donaturData =
            FXCollections.observableArrayList();

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        zakatTable.setItems(getDonaturData());

        donatur.setCellValueFactory(new PropertyValueFactory<>("donatur"));
        jumlah.setCellValueFactory(new PropertyValueFactory<>("jumlah"));
        tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
    }

    /**
     * get User Data from DAO.
     *
     * @return Observable List
     */
    private ObservableList<ZakatMasuk> getDonaturData() {
        try {
            donaturData.addAll(dao.getAll());
        } catch (SQLException e) {
            log.error("An error occurred", e);
        }
        return donaturData;
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @FXML
    public void addListener() {
        ZakatMasuk temp = new ZakatMasuk();
        mainApp.editDonaturZakat(temp);
    }

    @FXML
    public void editListener() {
        ZakatMasuk selectedItem = zakatTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            mainApp.editDonaturZakat(selectedItem);
        } else {
            org.masjidku.util.AlertHelper.alertError(dialogStage, "Null Error", "Data tidak ditemukan!");
        }
    }

    @FXML
    public void onRemoveListener() {
        ZakatMasuk selectedItem = zakatTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            try {
                if (dao.isDonaturExist(selectedItem.getId())) {
                    zakatTable.getItems().remove(selectedItem);
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
    public void onMouseClicked() { org.masjidku.util.AlertHelper.handleTableSelection(zakatTable, btnEdit, btnRemove); }

    @FXML
    public void gotoHome() {
        mainApp.showZakat();
    }


}
