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
import org.masjidku.accounting.client.model.anakyatim.AnakYatim;
import org.masjidku.accounting.client.model.anakyatim.DonasiAYatim;




import org.masjidku.accounting.client.service.AccountingClient;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccountantAnakyatim extends BaseAccountantController {
    private final AccountingClient client = ServiceProvider.get(AccountingClient.class);
    private static final Logger log = LoggerFactory.getLogger(AccountantAnakyatim.class);
    private AppRouter mainApp;

    public void setMainApp(AppRouter mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            AnakYatim penerima = client.getLastAnakYatim();
            DonasiAYatim pemberi = client.getLastDonasiAYatim();

            updateDashboardSummary(
                    pemberi.getJumlah(), // Pemasukan
                    penerima.getJumlah(), // Pengeluaran
                    client.getTotalDonasiAYatim(), // Total Pemasukan
                    client.getTotalAnakYatim(), // Total Pengeluaran
                    client.getInfakYatimBalance(), // Saldo
                    pemberi.getTanggal(), // Tgl Pemasukan
                    penerima.getTanggal() // Tgl Pengeluaran
            );
        } catch (Exception e) {
            log.error("An error occurred", e);
        }
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @FXML
    public void onKelolaDonasiAYatim() {
        mainApp.showDonasiAYatim();
    }

    @FXML
    public void onKelolaDanaAYatim() {
        mainApp.showDaftarAnakYatim();
    }
}


