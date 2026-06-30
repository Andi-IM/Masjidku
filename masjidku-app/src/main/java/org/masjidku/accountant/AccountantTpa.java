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
import org.masjidku.navigation.AppRouter;
import org.masjidku.accounting.client.model.tpa.TpaKeluar;
import org.masjidku.accounting.client.model.tpa.TpaMasuk;




import org.masjidku.accounting.client.service.AccountingClient;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccountantTpa extends BaseAccountantController {
    private final AccountingClient client = ServiceProvider.get(AccountingClient.class);
    private static final Logger log = LoggerFactory.getLogger(AccountantTpa.class);
    private AppRouter mainApp;

    public void setMainApp(AppRouter mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @FXML
    public void onKelolaTpa() {
        mainApp.showAlokasiTpa();
    }

    @FXML
    public void onKelolaDonasiTpa() {
        mainApp.showDonaturTpa();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            TpaKeluar penerima = client.getLastTpaKeluar();
            TpaMasuk pemberi = client.getLastTpaMasuk();

            updateDashboardSummary(
                    pemberi.getJumlah(), // Pemasukan
                    penerima.getJumlah(), // Pengeluaran
                    client.getTotalTpaMasuk(), // Total Pemasukan
                    client.getTotalTpaKeluar(), // Total Pengeluaran
                    client.getTpaBalance(), // Saldo
                    pemberi.getTanggal(), // Tgl Pemasukan
                    penerima.getTanggal() // Tgl Pengeluaran
            );

        } catch (Exception e) {
            log.error("An error occurred", e);
        }
    }
}


