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
import org.masjidku.model.kegiatan.TamuKegiatan;
import org.masjidku.model.kegiatan.TamuKegiatanDao;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListUndangan implements Initializable {

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
    @FXML
    public TableColumn<TamuKegiatan, String> colOperator;

    private MainApp mainApp;
    private TamuKegiatanDao dao;

    public void SecretaryUndangan() { dao = new TamuKegiatanDao(); }

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
        colOperator.setCellValueFactory(new PropertyValueFactory<>("operator"));
    }

    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }

    @FXML
    public void showReport() { }
}
