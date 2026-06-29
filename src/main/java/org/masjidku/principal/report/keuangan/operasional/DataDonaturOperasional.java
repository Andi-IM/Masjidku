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

package org.masjidku.principal.report.keuangan.operasional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.masjidku.MainApp;
import org.masjidku.model.accounting.operasional.DonasiOperasional;
import org.masjidku.model.accounting.operasional.DonasiOperationalDao;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataDonaturOperasional implements Initializable {
    @FXML
    private TableView<DonasiOperasional> tableOperasional;
    @FXML
    private TableColumn<DonasiOperasional, String> donatur;
    @FXML
    private TableColumn<DonasiOperasional, String> jumlah;
    @FXML
    private TableColumn<DonasiOperasional, String> tanggal;
    @FXML
    private TableColumn<DonasiOperasional, String> operator;

    private MainApp mainApp;

    /**
     * The data as an observable list of Donatur.
     */
    private final ObservableList<DonasiOperasional> donaturData =
            FXCollections.observableArrayList();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * get AnakYatim Data from DAO.
     *
     * @return Observable List
     */
    private ObservableList<DonasiOperasional> getDonaturData() {
        DonasiOperationalDao dao = new DonasiOperationalDao();
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
        tableOperasional.setItems(getDonaturData());

        donatur.setCellValueFactory(new PropertyValueFactory<>("donatur"));
        jumlah.setCellValueFactory(new PropertyValueFactory<>("jumlah"));
        tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        operator.setCellValueFactory(new PropertyValueFactory<>("operator"));
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @FXML
    public void gotoHome() {
        mainApp.showOperasionalData();
    }

    @FXML
    public void showReport() { }
}
