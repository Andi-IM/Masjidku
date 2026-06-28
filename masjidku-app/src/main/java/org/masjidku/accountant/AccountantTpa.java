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
import org.masjidku.accounting.client.model.tpa.TpaKeluar;
import org.masjidku.accounting.client.model.tpa.TpaMasuk;
import org.masjidku.accounting.client.service.AccountingFunctionsService;
import org.masjidku.accounting.client.service.TpaKeluarService;
import org.masjidku.accounting.client.service.TpaMasukService;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccountantTpa extends BaseAccountantController {
    private static final Logger log = LoggerFactory.getLogger(AccountantTpa.class);
    private final AccountingFunctionsService df = ServiceProvider.get(AccountingFunctionsService.class);
    private final TpaKeluarService tpakdao = ServiceProvider.get(TpaKeluarService.class);
    private final TpaMasukService tpamdao = ServiceProvider.get(TpaMasukService.class);
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
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
            TpaKeluar penerima = tpakdao.getLastRecord();
            TpaMasuk pemberi = tpamdao.getLastRecord();

            updateDashboardSummary(
                    pemberi.getJumlah(), // Pemasukan
                    penerima.getJumlah(), // Pengeluaran
                    tpamdao.getTotalIncome(), // Total Pemasukan
                    tpakdao.getTotalOutcome(), // Total Pengeluaran
                    df.getTpaBalance(), // Saldo
                    pemberi.getTanggal(), // Tgl Pemasukan
                    penerima.getTanggal() // Tgl Pengeluaran
            );

        } catch (SQLException e) {
            log.error("An error occurred", e);
        }
    }
}
