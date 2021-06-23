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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.masjidku.MainApp;
import org.masjidku.model.accounting.zakat.ZakatKeluar;
import org.masjidku.model.accounting.zakat.ZakatKeluarDao;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataPenerimaZakat implements Initializable {
    @FXML
    private TableView<ZakatKeluar> tableZakat;
    @FXML
    private TableColumn<ZakatKeluar, String> nama;
    @FXML
    private TableColumn<ZakatKeluar, String> jumlah;
    @FXML
    private TableColumn<ZakatKeluar, String> tanggal;
    @FXML
    private TableColumn<ZakatKeluar, String> operator;
    private MainApp mainApp;

    /**
     * The data as an observable list of Penerima Zakat.
     */
    private final ObservableList<ZakatKeluar> dataZakat =
            FXCollections.observableArrayList();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * get User Data from DAO.
     *
     * @return Observable List
     */
    private ObservableList<ZakatKeluar> getDataZakat() {
        ZakatKeluarDao dao = new ZakatKeluarDao();
        if (dao.getConnection()) {
            try {
                dataZakat.addAll(dao.getAll());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dataZakat;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableZakat.setItems(getDataZakat());

        nama.setCellValueFactory(new PropertyValueFactory<>("tujuan"));
        jumlah.setCellValueFactory(new PropertyValueFactory<>("jumlah"));
        tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        operator.setCellValueFactory(new PropertyValueFactory<>("operator"));
    }

    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }

    @FXML
    public void showReport() {  }

    @FXML
    public void gotoHome() { mainApp.showZakatData(); }
}
