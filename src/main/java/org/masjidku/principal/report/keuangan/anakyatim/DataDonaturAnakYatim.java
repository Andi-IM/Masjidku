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

package org.masjidku.principal.report.keuangan.anakyatim;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.masjidku.MainApp;
import org.masjidku.model.accounting.anakyatim.DonasiAYatim;
import org.masjidku.model.accounting.anakyatim.DonasiAYatimDao;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataDonaturAnakYatim implements Initializable {
    @FXML
    private TableView<DonasiAYatim> tblAYMasuk;
    @FXML
    private TableColumn<DonasiAYatim, String> donatur;
    @FXML
    private TableColumn<DonasiAYatim, String> jumlah;
    @FXML
    private TableColumn<DonasiAYatim, String> tanggal;
    @FXML
    private TableColumn<DonasiAYatim, String> operator;

    private MainApp mainApp;

    /**
     * The data as an observable list of Donatur.
     */
    private final ObservableList<DonasiAYatim> donaturData =
            FXCollections.observableArrayList();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * get AnakYatim Data from DAO.
     *
     * @return Observable List
     */
    private ObservableList<DonasiAYatim> getDonaturData() {
        DonasiAYatimDao dao = new DonasiAYatimDao();
        if (dao.getConnection()) {
            try {
                donaturData.addAll(dao.getAll());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return donaturData;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblAYMasuk.setItems(getDonaturData());

        donatur.setCellValueFactory(new PropertyValueFactory<>("donatur"));
        jumlah.setCellValueFactory(new PropertyValueFactory<>("jumlah"));
        tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        operator.setCellValueFactory(new PropertyValueFactory<>("operator"));
    }


    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    /**
     * Navigate back to home.
     */
    @FXML
    public void gotoHome() {
        mainApp.showAnakYatimData();
    }


    @FXML
    public void printReport() { }
}
