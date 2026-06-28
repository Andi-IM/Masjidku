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

import java.util.ServiceLoader;
import org.masjidku.accounting.client.service.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import org.masjidku.MainApp;
import org.masjidku.accounting.client.model.pembangunan.DonasiPembangunan;
import org.masjidku.accounting.client.model.pembangunan.Pembangunan;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccountantPembangunan implements Initializable {
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

    public void setMainApp(MainApp mainApp) { this.mainApp = mainApp; }

    @FXML
    public void onKelolaUangPembangunan() { mainApp.showAlokasiPembangunan(); }

    @FXML
    public void onKelolaDonasiPembangunan() { mainApp.showDonaturPembangunan(); }

    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PembangunanService pbDao = ServiceLoader.load(PembangunanService.class).findFirst().orElseThrow();
        DonasiPembangunanService dpDao = ServiceLoader.load(DonasiPembangunanService.class).findFirst().orElseThrow();
        AccountingFunctionsService df = ServiceLoader.load(AccountingFunctionsService.class).findFirst().orElseThrow();

        try {
            Pembangunan penerima = pbDao.getLastRecord();
            DonasiPembangunan pemberi = dpDao.getLastRecord();

            txtPemasukanTerakhir.setText("Rp. " + penerima.getJumlah());
            txtPengeluaranTerakhir.setText("Rp. " + pemberi.getJumlah());
            txtTotalPemasukkan.setText("Rp. " + pbDao.getTotalIncome());
            txtTotalPengeluaran.setText("Rp. " + dpDao.getTotalOutcome());
            txtSaldo.setText("Rp. " + df.getInfakYatimBalance());
            txtTglPemasukkan.setText(pemberi.getTanggal());
            txtTglPengeluaran.setText(penerima.getTanggal());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
