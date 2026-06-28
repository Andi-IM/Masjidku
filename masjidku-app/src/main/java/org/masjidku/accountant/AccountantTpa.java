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
import org.masjidku.accounting.client.model.tpa.TpaKeluar;
import org.masjidku.accounting.client.model.tpa.TpaMasuk;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccountantTpa implements Initializable {
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
    public Text txtTglPengeluaran;
    @FXML
    public Text txtSaldo;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) { this.mainApp = mainApp; }

    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }

    @FXML
    public void onKelolaTpa() { mainApp.showAlokasiTpa(); }

    @FXML
    public void onKelolaDonasiTpa() { mainApp.showDonaturTpa(); }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TpaMasukService tpamdao = ServiceLoader.load(TpaMasukService.class).findFirst().orElseThrow();
        TpaKeluarService tpakdao = ServiceLoader.load(TpaKeluarService.class).findFirst().orElseThrow();
        AccountingFunctionsService df = ServiceLoader.load(AccountingFunctionsService.class).findFirst().orElseThrow();

        try {
            if (tpamdao.getConnection() && tpakdao.getConnection() && df.getConnection()) {
                TpaKeluar penerima = tpakdao.getLastRecord();
                TpaMasuk pemberi = tpamdao.getLastRecord();

                txtPemasukanTerakhir.setText("Rp. " + pemberi.getJumlah());
                txtPengeluaranTerakhir.setText("Rp. " + penerima.getJumlah());
                txtTotalPemasukkan.setText("Rp. " + tpamdao.getTotalIncome());
                txtTotalPengeluaran.setText("Rp. " + tpakdao.getTotalOutcome());
                txtSaldo.setText("Rp. " + df.getTpaBalance());
                txtTglPemasukkan.setText(pemberi.getTanggal());
                txtTglPengeluaran.setText(penerima.getTanggal());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
