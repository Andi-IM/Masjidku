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

package org.masjidku.principal.report.kegiatan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.masjidku.util.ServiceProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.masjidku.MainApp;
import org.masjidku.events.client.model.Kegiatan;
import org.masjidku.events.client.service.KegiatanService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListKegiatan implements Initializable {
    private static final Logger log = LoggerFactory.getLogger(ListKegiatan.class);
    @FXML
    public TableView<Kegiatan> tblKegiatan;
    @FXML
    public TableColumn<Kegiatan, String> colNomor;
    @FXML
    public TableColumn<Kegiatan, String> colNmKegiatan;
    @FXML
    public TableColumn<Kegiatan, String> colTanggalKegiatan;
    @FXML
    public TableColumn<Kegiatan, String> colTempatKegiatan;
    @FXML
    public TableColumn<Kegiatan, String> colWaktuKegiatan;
    @FXML
    public TableColumn<Kegiatan, String> colOperator;
    public MainApp mainApp;
    private final KegiatanService dao;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public ListKegiatan(){
        dao = ServiceProvider.get(KegiatanService.class);
    }

    /**
     * The data as an observable list of Users.
     */
    private final ObservableList<Kegiatan> kegiatanData =
            FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblKegiatan.setItems(getKegiatanData());
        colNomor.setCellValueFactory(new PropertyValueFactory<>(""));
        colNmKegiatan.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colTempatKegiatan.setCellValueFactory(new PropertyValueFactory<>("tempat"));
        colWaktuKegiatan.setCellValueFactory(new PropertyValueFactory<>("waktu"));
        colTanggalKegiatan.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        colOperator.setCellValueFactory(new PropertyValueFactory<>("operator"));
    }

    private ObservableList<Kegiatan> getKegiatanData() {
            try {
                kegiatanData.addAll(dao.getAll());
            } catch (SQLException e) {
                log.error("An error occurred", e);
            }
        
        return kegiatanData;
    }

    @FXML
    public void printReport() {
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @FXML
    public void gotoHome() { mainApp.showKegiatanOverview(); }
}
