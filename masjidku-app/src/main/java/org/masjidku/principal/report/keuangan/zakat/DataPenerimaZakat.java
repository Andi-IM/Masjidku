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

import javafx.fxml.FXML;

import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import org.masjidku.MainApp;
import org.masjidku.accounting.client.model.zakat.ZakatKeluar;
import org.masjidku.accounting.client.service.ZakatKeluarService;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataPenerimaZakat extends org.masjidku.accountant.BaseTableController<ZakatKeluar> {
    private static final Logger log = LoggerFactory.getLogger(DataPenerimaZakat.class);
    private final ZakatKeluarService dao = ServiceProvider.get(ZakatKeluarService.class);
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

    

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    

    @Override
    protected void setupTableColumns() {
        org.masjidku.util.AlertHelper.setupOutflowColumns(nama, jumlah, tanggal);
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
        mainApp.showZakatData();
    }

    @Override protected org.slf4j.Logger getLogger() { return log; }
    @Override protected TableView<ZakatKeluar> getTableView() { return tableZakat; }
    @Override protected Button getBtnEdit() { return null; }
    @Override protected Button getBtnRemove() { return null; }
    @Override protected List<ZakatKeluar> fetchAllData() throws java.sql.SQLException { return dao.getAll(); }
    @Override protected boolean checkIfExist(ZakatKeluar item) throws java.sql.SQLException { return false; }
    @Override protected void deleteItem(ZakatKeluar item) throws java.sql.SQLException {  }
    @Override protected void handleEdit(ZakatKeluar item) {  }
}

