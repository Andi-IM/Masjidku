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
import org.masjidku.accounting.client.model.zakat.ZakatKeluar;
import org.masjidku.accounting.client.model.zakat.ZakatMasuk;




import org.masjidku.accounting.client.service.AccountingClient;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccountantZakat extends BaseAccountantController {
    private final AccountingClient client = ServiceProvider.get(AccountingClient.class);
    private static final Logger log = LoggerFactory.getLogger(AccountantZakat.class);
    private AppRouter mainApp;

    public void setMainApp(AppRouter mainApp) {
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
            ZakatKeluar penerima = client.getLastZakatKeluar();
            ZakatMasuk pemberi = client.getLastZakatMasuk();

            updateDashboardSummary(
                    pemberi.jumlah(), // Pemasukan
                    penerima.jumlah(), // Pengeluaran
                    client.getTotalZakatMasuk(), // Total Pemasukan
                    client.getTotalZakatKeluar(), // Total Pengeluaran
                    client.getZakatBalance(), // Saldo
                    pemberi.tanggal(), // Tgl Pemasukan
                    penerima.tanggal() // Tgl Pengeluaran
            );
        } catch (Exception e) {
            log.error("An error occurred", e);
        }
    }
}


