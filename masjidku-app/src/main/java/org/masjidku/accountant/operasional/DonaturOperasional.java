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

package org.masjidku.accountant.operasional;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.masjidku.controller.BaseTableController;
import org.masjidku.navigation.AppRouter;
import org.masjidku.accounting.client.model.operasional.DonasiOperasional;

import org.masjidku.accounting.client.service.AccountingClient;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DonaturOperasional extends BaseTableController<DonasiOperasional> {
    private static final Logger log = LoggerFactory.getLogger(DonaturOperasional.class);
    private final AccountingClient client = ServiceProvider.get(AccountingClient.class);

    @FXML
    private TableView<DonasiOperasional> tableOperasional;
    @FXML
    private TableColumn<DonasiOperasional, String> donatur;
    @FXML
    private TableColumn<DonasiOperasional, String> jumlah;
    @FXML
    private TableColumn<DonasiOperasional, String> tanggal;
    @Override
    protected void setupTableColumns() {
        org.masjidku.util.AlertHelper.setupInflowColumns(donatur, jumlah, tanggal);
}

    @FXML
    public void addUserListener() {
        DonasiOperasional temp = new DonasiOperasional();
        mainApp.editDonaturOperasional(temp);
    }

    @FXML
    public void gotoHome() {
        mainApp.showOperasional();
    }

    @Override
    protected org.slf4j.Logger getLogger() {
        return log;
    }

    @Override
    protected TableView<DonasiOperasional> getTableView() {
        return tableOperasional;
    }

    @Override
    protected List<DonasiOperasional> fetchAllData() throws java.sql.SQLException {
        return client.getAllDonasiOperasional();
    }

    @Override
    protected boolean checkIfExist(DonasiOperasional item) throws java.sql.SQLException {
        return client.isDonasiOperasionalExist(item.id());
    }

    @Override
    protected void deleteItem(DonasiOperasional item) throws java.sql.SQLException {
        client.delete(item);
    }

    @Override
    protected void handleEdit(DonasiOperasional item) {
        mainApp.editDonaturOperasional(item);
    }

    }

