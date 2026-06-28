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
import org.masjidku.accounting.client.model.pembangunan.DonasiPembangunan;
import org.masjidku.accounting.client.model.pembangunan.Pembangunan;
import org.masjidku.accounting.client.service.AccountingFunctionsService;
import org.masjidku.accounting.client.service.DonasiPembangunanService;
import org.masjidku.accounting.client.service.PembangunanService;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccountantPembangunan extends BaseAccountantController {
    private static final Logger log = LoggerFactory.getLogger(AccountantPembangunan.class);
    private final AccountingFunctionsService df = ServiceProvider.get(AccountingFunctionsService.class);
    private final DonasiPembangunanService dpDao = ServiceProvider.get(DonasiPembangunanService.class);
    private final PembangunanService pbDao = ServiceProvider.get(PembangunanService.class);
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void onKelolaUangPembangunan() {
        mainApp.showAlokasiPembangunan();
    }

    @FXML
    public void onKelolaDonasiPembangunan() {
        mainApp.showDonaturPembangunan();
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Pembangunan penerima = pbDao.getLastRecord();
            DonasiPembangunan pemberi = dpDao.getLastRecord();

            updateDashboardSummary(
                    pemberi.getJumlah(), // Pemasukan
                    penerima.getJumlah(), // Pengeluaran
                    pbDao.getTotalIncome(), // Total Pemasukan
                    dpDao.getTotalOutcome(), // Total Pengeluaran
                    df.getPembangunanBalance(), // Saldo
                    pemberi.getTanggal(), // Tgl Pemasukan
                    penerima.getTanggal() // Tgl Pengeluaran
            );

        } catch (SQLException e) {
            log.error("An error occurred", e);
        }
    }
}
