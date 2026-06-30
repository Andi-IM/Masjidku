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
import org.masjidku.accounting.client.model.pembangunan.DonasiPembangunan;
import org.masjidku.accounting.client.model.pembangunan.Pembangunan;
import org.masjidku.accounting.client.service.AccountingClient;
import org.masjidku.navigation.AppRouter;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountantPembangunan extends BaseAccountantController {
    private final AccountingClient client = ServiceProvider.get(AccountingClient.class);
    private static final Logger log = LoggerFactory.getLogger(AccountantPembangunan.class);
    private AppRouter mainApp;

    public void setMainApp(AppRouter mainApp) {
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
            Pembangunan penerima = client.getLastPembangunan();
            DonasiPembangunan pemberi = client.getLastDonasiPembangunan();

            updateDashboardSummary(
                    pemberi.jumlah(), // Pemasukan
                    penerima.jumlah(), // Pengeluaran
                    client.getTotalPembangunan(), // Total Pemasukan
                    client.getTotalDonasiPembangunan(), // Total Pengeluaran
                    client.getPembangunanBalance(), // Saldo
                    pemberi.tanggal(), // Tgl Pemasukan
                    penerima.tanggal() // Tgl Pengeluaran
            );

        } catch (Exception e) {
            log.error("An error occurred", e);
        }
    }
}


