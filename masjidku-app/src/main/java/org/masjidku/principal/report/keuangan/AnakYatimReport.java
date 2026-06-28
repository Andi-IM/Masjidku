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

package org.masjidku.principal.report.keuangan;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
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

public class AnakYatimReport implements Initializable {
    private static final Logger log = LoggerFactory.getLogger(AnakYatimReport.class);
    private final AccountingFunctionsService df = ServiceProvider.get(AccountingFunctionsService.class);
    private final AnakYatimService ayDao = ServiceProvider.get(AnakYatimService.class);
    private final DonasiAYatimService dayDao = ServiceProvider.get(DonasiAYatimService.class);
    @FXML
    public Text txtPemasukanTerakhir;
    @FXML
    public Text txtPengeluaranTerakhir;
    @FXML
    public Text txtTglPemasukkan;
    @FXML
    public Text txtTotalPemasukkan;
    @FXML
    public Text txtTotalPengeluaran;
    @FXML
    public Text txtSaldo;
    @FXML
    public Text txtTglPengeluaran;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            AnakYatim penerima = ayDao.getLastRecord();
            DonasiAYatim pemberi = dayDao.getLastRecord();

            txtPemasukanTerakhir.setText("Rp. " + penerima.getJumlah());
            txtPengeluaranTerakhir.setText("Rp. " + pemberi.getJumlah());
            txtTotalPemasukkan.setText("Rp. " + dayDao.getTotalIncome());
            txtTotalPengeluaran.setText("Rp. " + ayDao.getTotalOutcome());
            txtSaldo.setText("Rp. " + df.getInfakYatimBalance());
            txtTglPemasukkan.setText(pemberi.getTanggal());
            txtTglPengeluaran.setText(penerima.getTanggal());

        } catch (SQLException e) {
            log.error("An error occurred", e);
        }

    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @FXML
    public void laporanDonasiAnakYatim() {
        mainApp.showAnakYatimMasuk();
    }

    @FXML
    public void laporanDanaAnakYatim() {
        mainApp.showAnakYatimKeluar();
    }

    @FXML
    public void gotoHome() {
        mainApp.showData();
    }
}
