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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.masjidku.accounting.client.model.pembangunan.DonasiPembangunan;
import org.masjidku.accounting.client.service.AccountingClient;
import org.masjidku.controller.BaseTableController;
import org.masjidku.navigation.AppRouter;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DonaturPembangunan extends BaseTableController<DonasiPembangunan> {
    private static final Logger log = LoggerFactory.getLogger(DonaturPembangunan.class);
    private final AccountingClient client = ServiceProvider.get(AccountingClient.class);

    @FXML
    private TableView<DonasiPembangunan> tablePembangunan;
    @FXML
    private TableColumn<DonasiPembangunan, String> donatur;
    @FXML
    private TableColumn<DonasiPembangunan, String> jumlah;
    @FXML
    private TableColumn<DonasiPembangunan, String> tanggal;
    @Override
    protected void setupTableColumns() {
        org.masjidku.util.AlertHelper.setupInflowColumns(donatur, jumlah, tanggal);
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
    protected List<DonasiPembangunan> fetchAllData() {
        return client.getAllDonasiPembangunan();
    }

    @Override
    protected boolean checkIfExist(DonasiPembangunan item) {
        return client.isDonasiPembangunanExist(item.id());
    }

    @Override
    protected void deleteItem(DonasiPembangunan item) {
        client.delete(item);
    }

    @Override
    protected void handleEdit(DonasiPembangunan item) {
        mainApp.editDonaturPembangunan(item);
    }

    }

