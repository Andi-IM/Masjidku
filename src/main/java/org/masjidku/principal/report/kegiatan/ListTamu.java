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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.masjidku.MainApp;
import org.masjidku.model.kegiatan.Tamu;
import org.masjidku.model.kegiatan.TamuDao;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListTamu implements Initializable {
    @FXML
    public TableView<Tamu> tblTamu;
    @FXML
    public TableColumn<Tamu, String> colNama;
    @FXML
    public TableColumn<Tamu, String> colAlamat;
    @FXML
    public TableColumn<Tamu, String> colNotelp;
    @FXML
    public TableColumn<Tamu, String> colNomor;
    @FXML
    public TableColumn<Tamu, String> colOperator;

    private MainApp mainApp;
    TamuDao dao;

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * The Constructor
     * The Constructor is called before the initialize() method.
     */
    public ListTamu() { dao = new TamuDao(); }

    private ObservableList<Tamu> getTamuData() {
        if (dao.getConnection()){
            try {
                tamuData.addAll(dao.getAll());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tamuData;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblTamu.setItems(getTamuData());
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        colNotelp.setCellValueFactory(new PropertyValueFactory<>("notelp"));
        colOperator.setCellValueFactory(new PropertyValueFactory<>("operator"));
    }

    /**
     * The data as an observable list of Users.
     */
    private final ObservableList<Tamu> tamuData =
            FXCollections.observableArrayList();

    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }

    @FXML
    public void showReport() {
    }
}
