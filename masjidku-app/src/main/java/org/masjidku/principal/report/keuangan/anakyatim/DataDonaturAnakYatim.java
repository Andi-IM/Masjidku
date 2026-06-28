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

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.masjidku.MainApp;
import org.masjidku.accounting.client.model.anakyatim.DonasiAYatim;
import org.masjidku.accounting.client.service.DonasiAYatimService;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DataDonaturAnakYatim extends org.masjidku.accountant.BaseTableController<DonasiAYatim> {
    private static final Logger log = LoggerFactory.getLogger(DataDonaturAnakYatim.class);
    private final DonasiAYatimService dao = ServiceProvider.get(DonasiAYatimService.class);
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


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


    @Override
    protected void setupTableColumns() {
        org.masjidku.util.AlertHelper.setupInflowColumns(donatur, jumlah, tanggal);
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
    public void printReport() {
    }

    @Override
    protected org.slf4j.Logger getLogger() {
        return log;
    }

    @Override
    protected TableView<DonasiAYatim> getTableView() {
        return tblAYMasuk;
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
    protected List<DonasiAYatim> fetchAllData() throws java.sql.SQLException {
        return dao.getAll();
    }

    @Override
    protected boolean checkIfExist(DonasiAYatim item) {
        return false;
    }

    @Override
    protected void deleteItem(DonasiAYatim item) {
    }

    @Override
    protected void handleEdit(DonasiAYatim item) {
    }
}

