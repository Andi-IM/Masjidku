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
import org.masjidku.accounting.client.model.operasional.DonasiOperasional;
import org.masjidku.accounting.client.model.operasional.Operasional;
import org.masjidku.accounting.client.service.AccountingFunctionsService;
import org.masjidku.accounting.client.service.DonasiOperationalService;
import org.masjidku.accounting.client.service.OperationalService;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccountantOperasional extends BaseAccountantController {
    private static final Logger log = LoggerFactory.getLogger(AccountantOperasional.class);
    private final AccountingFunctionsService df = ServiceProvider.get(AccountingFunctionsService.class);
    private final DonasiOperationalService doDao = ServiceProvider.get(DonasiOperationalService.class);
    private final OperationalService opDao = ServiceProvider.get(OperationalService.class);
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @FXML
    public void onKelolaDonaturClick() {
        mainApp.showDonaturOperasional();
    }

    @FXML
    public void onKelolaOperasionalClick() {
        mainApp.showAlokasiOperasional();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Operasional penerima = opDao.getLastRecord();
            DonasiOperasional pemberi = doDao.getLastRecord();

            updateDashboardSummary(
                    pemberi.getJumlah(), // Pemasukan
                    penerima.getJumlah(), // Pengeluaran
                    opDao.getTotalIncome(), // Total Pemasukan
                    doDao.getTotalOutcome(), // Total Pengeluaran
                    df.getOperationalBalance(),
                    pemberi.getTanggal(),
                    penerima.getTanggal()
            );

        } catch (SQLException e) {
            log.error("An error occurred", e);
        }
    }
}
