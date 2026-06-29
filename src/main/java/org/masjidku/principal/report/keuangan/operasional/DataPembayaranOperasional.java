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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.masjidku.MainApp;
import org.masjidku.model.accounting.operasional.Operasional;
import org.masjidku.model.accounting.operasional.OperationalDao;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataPembayaranOperasional implements Initializable {
    @FXML
    private TableView<Operasional> tableOperasional;
    @FXML
    private TableColumn<Operasional, String> nama;
    @FXML
    private TableColumn<Operasional, String> keterangan;
    @FXML
    private TableColumn<Operasional, String> jumlah;
    @FXML
    private TableColumn<Operasional, String> tanggal;
    @FXML
    private TableColumn<Operasional, String> operator;
    private MainApp mainApp;

    /**
     * The data as an observable list of Data Operasional.
     */
    private final ObservableList<Operasional> dataOperasional =
            FXCollections.observableArrayList();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * get User Data from DAO.
     *
     * @return Observable List
     */
    private ObservableList<Operasional> getDataOperasional() {
        OperationalDao dao = new OperationalDao();
        if (dao.getConnection()) {
            try {
                dataOperasional.addAll(dao.getAll());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dataOperasional;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableOperasional.setItems(getDataOperasional());

        nama.setCellValueFactory(new PropertyValueFactory<>("tujuan"));
        keterangan.setCellValueFactory(new PropertyValueFactory<>("keterangan"));
        jumlah.setCellValueFactory(new PropertyValueFactory<>("jumlah"));
        tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        operator.setCellValueFactory(new PropertyValueFactory<>("operator"));
    }

    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }

    @FXML
    public void gotoHome() { mainApp.showOperasionalData(); }

    @FXML
    public void showReport() { }
}
