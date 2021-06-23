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
import org.masjidku.model.accounting.anakyatim.AnakYatim;
import org.masjidku.model.accounting.anakyatim.AnakYatimDao;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataPenerimaAnakYatim implements Initializable {

    @FXML
    private TableView<AnakYatim> tableAnakyatim;
    @FXML
    private TableColumn<AnakYatim, String> nama;
    @FXML
    private TableColumn<AnakYatim, String> jumlah;
    @FXML
    private TableColumn<AnakYatim, String> usia;
    @FXML
    private TableColumn<AnakYatim, String> tanggal;
    @FXML
    private TableColumn<AnakYatim, String> operator;

    private MainApp mainApp;

    /**
     * The data as an observable list of Anak Yatim.
     */
    private final ObservableList<AnakYatim> dataAnak =
            FXCollections.observableArrayList();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * get User Data from DAO.
     *
     * @return Observable List
     */
    private ObservableList<AnakYatim> getDataAnak() {
        AnakYatimDao dao = new AnakYatimDao();
        if (dao.getConnection()) {
            try {
                dataAnak.addAll(dao.getAll());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dataAnak;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableAnakyatim.setItems(getDataAnak());

        nama.setCellValueFactory(new PropertyValueFactory<>("tujuan"));
        usia.setCellValueFactory(new PropertyValueFactory<>("usia"));
        jumlah.setCellValueFactory(new PropertyValueFactory<>("jumlah"));
        tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        operator.setCellValueFactory(new PropertyValueFactory<>("operator"));
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @FXML
    public void gotoHome() { mainApp.showAnakYatimData(); }

    @FXML
    public void printReport() { }
}
