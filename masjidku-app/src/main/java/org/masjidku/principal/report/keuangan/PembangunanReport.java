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
import org.masjidku.accounting.client.model.pembangunan.DonasiPembangunan;
import org.masjidku.accounting.client.model.pembangunan.Pembangunan;
import org.masjidku.accounting.client.service.AccountingClient;
import org.masjidku.navigation.AppRouter;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class PembangunanReport implements Initializable {
    private final AccountingClient client = ServiceProvider.get(AccountingClient.class);
    private static final Logger log = LoggerFactory.getLogger(PembangunanReport.class);
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

    private AppRouter mainApp;

    public void setMainApp(AppRouter mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void uangMasuk() {
        mainApp.showPembangunanMasuk();
    }

    @FXML
    public void uangKeluar() {
        mainApp.showPembangunanKeluar();
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

            txtPemasukanTerakhir.setText("Rp. " + penerima.jumlah());
            txtPengeluaranTerakhir.setText("Rp. " + pemberi.jumlah());
            txtTotalPemasukkan.setText("Rp. " + client.getTotalPembangunan());
            txtTotalPengeluaran.setText("Rp. " + client.getTotalDonasiPembangunan());
            txtSaldo.setText("Rp. " + client.getPembangunanBalance());
            txtTglPemasukkan.setText(pemberi.tanggal().toString());
            txtTglPengeluaran.setText(penerima.tanggal().toString());

        } catch (Exception e) {
            log.error("An error occurred", e);
        }
    }

    @FXML
    public void gotoHome() {
        mainApp.showData();
    }
}


