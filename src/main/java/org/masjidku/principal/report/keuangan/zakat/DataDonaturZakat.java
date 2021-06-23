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

package org.masjidku.principal.report.keuangan.zakat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.masjidku.MainApp;
import org.masjidku.model.accounting.zakat.ZakatMasuk;
import org.masjidku.model.accounting.zakat.ZakatMasukDao;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataDonaturZakat implements Initializable {
    @FXML
    private TableView<ZakatMasuk> zakatTable;
    @FXML
    private TableColumn<ZakatMasuk, String> donatur;
    @FXML
    private TableColumn<ZakatMasuk, String> jumlah;
    @FXML
    private TableColumn<ZakatMasuk, String> tanggal;
    @FXML
    private TableColumn<ZakatMasuk, String> operator;

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
        operator.setCellValueFactory(new PropertyValueFactory<>("operator"));
    }

    /**
     * get User Data from DAO.
     *
     * @return Observable List
     */
    private ObservableList<ZakatMasuk> getDonaturData() {
        ZakatMasukDao dao = new ZakatMasukDao();
        if (dao.getConnection()) {
            try {
                donaturData.addAll(dao.getAll());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return donaturData;
    }

    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }

    @FXML
    public void showReport() { }

    @FXML
    public void gotoHome() { mainApp.showZakatData(); }
}
