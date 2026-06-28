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

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.masjidku.MainApp;
import org.masjidku.accounting.client.model.pembangunan.Pembangunan;
import org.masjidku.accounting.client.service.PembangunanService;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DataPembayaranPembangunan extends org.masjidku.accountant.BaseTableController<Pembangunan> {
    private static final Logger log = LoggerFactory.getLogger(DataPembayaranPembangunan.class);
    private final PembangunanService dao = ServiceProvider.get(PembangunanService.class);
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


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


    @Override
    protected void setupTableColumns() {
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
    public void showReport() {
    }

    @FXML
    public void gotoHome() {
        mainApp.showPembangunanData();
    }

    @Override
    protected org.slf4j.Logger getLogger() {
        return log;
    }

    @Override
    protected TableView<Pembangunan> getTableView() {
        return tablePembangunan;
    }

    @Override
    protected Button getBtnEdit() {
        return null;
    }

    @Override
    protected Button getBtnRemove() {
        return null;
    }

    @Override
    protected List<Pembangunan> fetchAllData() throws java.sql.SQLException {
        return dao.getAll();
    }

    @Override
    protected boolean checkIfExist(Pembangunan item) throws java.sql.SQLException {
        return false;
    }

    @Override
    protected void deleteItem(Pembangunan item) throws java.sql.SQLException {
    }

    @Override
    protected void handleEdit(Pembangunan item) {
    }
}

