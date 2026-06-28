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

package org.masjidku.accountant.pembangunan;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.masjidku.MainApp;
import org.masjidku.accounting.client.model.pembangunan.DonasiPembangunan;
import org.masjidku.accounting.client.service.DonasiPembangunanService;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DonaturPembangunan extends org.masjidku.accountant.BaseTableController<DonasiPembangunan> {
    private static final Logger log = LoggerFactory.getLogger(DonaturPembangunan.class);
    private final DonasiPembangunanService dao = ServiceProvider.get(DonasiPembangunanService.class);

    @FXML
    private TableView<DonasiPembangunan> tablePembangunan;
    @FXML
    private TableColumn<DonasiPembangunan, String> donatur;
    @FXML
    private TableColumn<DonasiPembangunan, String> jumlah;
    @FXML
    private TableColumn<DonasiPembangunan, String> tanggal;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnRemove;

    private MainApp mainApp;


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


    @Override
    protected void setupTableColumns() {
        org.masjidku.util.AlertHelper.setupInflowColumns(donatur, jumlah, tanggal);
}

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }


    @FXML
    public void addListener() {
        DonasiPembangunan temp = new DonasiPembangunan();
        mainApp.editDonaturPembangunan(temp);
    }


    @FXML
    public void gotoHome() {
        mainApp.showPembangunan();
    }


    @Override
    protected org.slf4j.Logger getLogger() {
        return log;
    }

    @Override
    protected TableView<DonasiPembangunan> getTableView() {
        return tablePembangunan;
    }

    @Override
    protected Button getBtnEdit() {
        return btnEdit;
    }

    @Override
    protected Button getBtnRemove() {
        return btnRemove;
    }

    @Override
    protected List<DonasiPembangunan> fetchAllData() throws java.sql.SQLException {
        return dao.getAll();
    }

    @Override
    protected boolean checkIfExist(DonasiPembangunan item) throws java.sql.SQLException {
        return dao.isDonaturExist(item.getId());
    }

    @Override
    protected void deleteItem(DonasiPembangunan item) throws java.sql.SQLException {
        dao.delete(item.getId());
    }

    @Override
    protected void handleEdit(DonasiPembangunan item) {
        mainApp.editDonaturPembangunan(item);
    }

    @FXML
    public void onEditListener() {
        super.onEditAction();
    }
}
