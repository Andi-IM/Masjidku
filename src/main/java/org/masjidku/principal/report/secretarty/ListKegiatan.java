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

package org.masjidku.principal.report.secretarty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.masjidku.MainApp;
import org.masjidku.model.kegiatan.Kegiatan;
import org.masjidku.model.kegiatan.KegiatanDao;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListKegiatan implements Initializable {
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
    KegiatanDao dao;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * The data as an observable list of Users.
     */
    private final ObservableList<Kegiatan> kegiatanData =
            FXCollections.observableArrayList();

    private ObservableList<Kegiatan> getKegiatanData() {
        if (dao.getConnection()) {
            try {
                kegiatanData.addAll(dao.getAll());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return kegiatanData;
    }

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

    @FXML
    public void printReport() {
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }
}
