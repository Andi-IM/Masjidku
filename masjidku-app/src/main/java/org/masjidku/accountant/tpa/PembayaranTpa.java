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

package org.masjidku.accountant.tpa;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.masjidku.accounting.client.model.tpa.TpaKeluar;
import org.masjidku.accounting.client.service.AccountingClient;
import org.masjidku.controller.BaseTableController;
import org.masjidku.navigation.AppRouter;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PembayaranTpa extends BaseTableController<TpaKeluar> {
    private static final Logger log = LoggerFactory.getLogger(PembayaranTpa.class);
    private final AccountingClient client = ServiceProvider.get(AccountingClient.class);

    @FXML
    private TableView<TpaKeluar> tableTpa;
    @FXML
    private TableColumn<TpaKeluar, String> nama;
    @FXML
    private TableColumn<TpaKeluar, String> jumlah;
    @FXML
    private TableColumn<TpaKeluar, String> tanggal;
    @Override
    protected void setupTableColumns() {
        org.masjidku.util.AlertHelper.setupOutflowColumns(nama, jumlah, tanggal);
    }

    @FXML
    public void addListener() {
        TpaKeluar temp = new TpaKeluar();
        mainApp.editAlokasiTpa(temp);
    }

    @FXML
    public void gotoHome() {
        mainApp.showTpa();
    }

    @Override
    protected org.slf4j.Logger getLogger() {
        return log;
    }

    @Override
    protected TableView<TpaKeluar> getTableView() {
        return tableTpa;
    }

    @Override
    protected List<TpaKeluar> fetchAllData() {
        return client.getAllTpaKeluar();
    }

    @Override
    protected boolean checkIfExist(TpaKeluar item) {
        return client.isTpaKeluarExist(item.id());
    }

    @Override
    protected void deleteItem(TpaKeluar item) {
        client.delete(item);
    }

    @Override
    protected void handleEdit(TpaKeluar item) {
        mainApp.editAlokasiTpa(item);
    }

    }

