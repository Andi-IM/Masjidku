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

package org.masjidku.accountant.anakyatim;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.masjidku.util.ServiceProvider;
import org.masjidku.accounting.client.service.*;
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
import org.masjidku.accounting.client.model.anakyatim.AnakYatim;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PenerimaAnakYatim implements Initializable {
    private static final Logger log = LoggerFactory.getLogger(PenerimaAnakYatim.class);
    private final AnakYatimService dao = ServiceProvider.get(AnakYatimService.class);

    @FXML
    private TableView<AnakYatim> tableAnakyatim;
    @FXML
    private TableColumn<AnakYatim, String> nama;
    @FXML
    private TableColumn<AnakYatim, String> jumlah;
    @FXML
    private TableColumn<AnakYatim, String> usia;
    @FXML
    private TableColumn<AnakYatim, String> tanggal;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnRemove;

    private MainApp mainApp;

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;

    /**
     * The data as an observable list of Anak Yatim.
     */
    private final ObservableList<AnakYatim> dataAnak =
            FXCollections.observableArrayList();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableAnakyatim.setItems(org.masjidku.util.AlertHelper.loadTableData(dataAnak, dao::getAll, log));

        nama.setCellValueFactory(new PropertyValueFactory<>("tujuan"));
        usia.setCellValueFactory(new PropertyValueFactory<>("usia"));
        jumlah.setCellValueFactory(new PropertyValueFactory<>("jumlah"));
        tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @FXML
    public void onMouseClicked() { org.masjidku.util.AlertHelper.handleTableSelection(tableAnakyatim, btnEdit, btnRemove); }

    @FXML
    public void onCreateListener() {
        AnakYatim temp = new AnakYatim();
        mainApp.editAnakYatim(temp);
    }

    @FXML
    public void onEditListener() {
        AnakYatim selectedItem = tableAnakyatim.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            mainApp.editAnakYatim(selectedItem);
        } else {
            org.masjidku.util.AlertHelper.alertError(dialogStage, "Null Error", "Data tidak ditemukan!");
        }
    }

    @FXML
    public void onRemoveListener() {
        AnakYatim selectedItem = tableAnakyatim.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            try {
                if (dao.isAnakYatimExist(selectedItem.getId())) {
                    tableAnakyatim.getItems().remove(selectedItem);
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
        mainApp.showAnakYatim();
    }

    

    
}
