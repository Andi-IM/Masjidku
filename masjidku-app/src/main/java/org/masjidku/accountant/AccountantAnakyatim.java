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
import org.masjidku.accounting.client.model.anakyatim.AnakYatim;
import org.masjidku.accounting.client.model.anakyatim.DonasiAYatim;
import org.masjidku.accounting.client.service.AccountingFunctionsService;
import org.masjidku.accounting.client.service.AnakYatimService;
import org.masjidku.accounting.client.service.DonasiAYatimService;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccountantAnakyatim extends BaseAccountantController {
    private static final Logger log = LoggerFactory.getLogger(AccountantAnakyatim.class);
    private final AccountingFunctionsService df = ServiceProvider.get(AccountingFunctionsService.class);
    private final AnakYatimService ayDao = ServiceProvider.get(AnakYatimService.class);
    private final DonasiAYatimService dayDao = ServiceProvider.get(DonasiAYatimService.class);
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            AnakYatim penerima = ayDao.getLastRecord();
            DonasiAYatim pemberi = dayDao.getLastRecord();

            updateDashboardSummary(
                    pemberi.getJumlah(), // Pemasukan
                    penerima.getJumlah(), // Pengeluaran
                    dayDao.getTotalIncome(), // Total Pemasukan
                    ayDao.getTotalOutcome(), // Total Pengeluaran
                    df.getInfakYatimBalance(), // Saldo
                    pemberi.getTanggal(), // Tgl Pemasukan
                    penerima.getTanggal() // Tgl Pengeluaran
            );
        } catch (SQLException e) {
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

