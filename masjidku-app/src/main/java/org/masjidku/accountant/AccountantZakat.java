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

package org.masjidku.accountant;

import javafx.fxml.FXML;
import org.masjidku.MainApp;
import org.masjidku.accounting.client.model.zakat.ZakatKeluar;
import org.masjidku.accounting.client.model.zakat.ZakatMasuk;
import org.masjidku.accounting.client.service.AccountingFunctionsService;
import org.masjidku.accounting.client.service.ZakatKeluarService;
import org.masjidku.accounting.client.service.ZakatMasukService;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccountantZakat extends BaseAccountantController {
    private static final Logger log = LoggerFactory.getLogger(AccountantZakat.class);
    private final AccountingFunctionsService df = ServiceProvider.get(AccountingFunctionsService.class);
    private final ZakatKeluarService zkDao = ServiceProvider.get(ZakatKeluarService.class);
    private final ZakatMasukService zmDao = ServiceProvider.get(ZakatMasukService.class);
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void onKelolaDonasiZakat() {
        mainApp.showDonaturZakat();
    }

    @FXML
    public void onKelolaPenerimaZakat() {
        mainApp.showDaftarPenerimaZakat();
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            ZakatKeluar penerima = zkDao.getLastRecord();
            ZakatMasuk pemberi = zmDao.getLastRecord();

            updateDashboardSummary(
                    pemberi.getJumlah(), // Pemasukan
                    penerima.getJumlah(), // Pengeluaran
                    zmDao.getTotalIncome(), // Total Pemasukan
                    zkDao.gettotalOutcome(), // Total Pengeluaran
                    df.getZakatBalance(), // Saldo
                    pemberi.getTanggal(), // Tgl Pemasukan
                    penerima.getTanggal() // Tgl Pengeluaran
            );
        } catch (SQLException e) {
            log.error("An error occurred", e);
        }
    }
}

