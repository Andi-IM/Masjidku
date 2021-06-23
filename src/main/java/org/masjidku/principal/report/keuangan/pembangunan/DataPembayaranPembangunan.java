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

package org.masjidku.principal.report.keuangan.pembangunan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.masjidku.MainApp;
import org.masjidku.model.accounting.pembangunan.Pembangunan;
import org.masjidku.model.accounting.pembangunan.PembangunanDao;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataPembayaranPembangunan implements Initializable {
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
    private TableColumn<Pembangunan, String> operator;

    private MainApp mainApp;

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
        operator.setCellValueFactory(new PropertyValueFactory<>("operator"));
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @FXML
    public void showReport() { }

    @FXML
    public void gotoHome() { mainApp.showPembangunanData(); }
}
