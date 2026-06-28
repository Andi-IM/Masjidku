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

import org.masjidku.util.ServiceProvider;
import java.util.ServiceLoader;
import org.masjidku.accounting.client.service.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import org.masjidku.MainApp;
import org.masjidku.accounting.client.model.zakat.ZakatKeluar;
import org.masjidku.accounting.client.model.zakat.ZakatMasuk;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccountantZakat implements Initializable {
    private final AccountingFunctionsService df = ServiceProvider.get(AccountingFunctionsService.class);
    private final ZakatKeluarService zkDao = ServiceProvider.get(ZakatKeluarService.class);
    private final ZakatMasukService zmDao = ServiceProvider.get(ZakatMasukService.class);
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
    public void onKelolaDonasiZakat() { mainApp.showDonaturZakat(); }

    @FXML
    public void onKelolaPenerimaZakat() { mainApp.showDaftarPenerimaZakat(); }

    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            ZakatKeluar penerima = zkDao.getLastRecord();
            ZakatMasuk pemberi = zmDao.getLastRecord();

            txtPemasukanTerakhir.setText("Rp. " + pemberi.getJumlah() );
            txtPengeluaranTerakhir.setText("Rp. " + penerima.getJumlah());
            txtTotalPemasukkan.setText("Rp. " + zmDao.getTotalIncome());
            txtTotalPengeluaran.setText("Rp. " + zkDao.gettotalOutcome());
            txtSaldo.setText("Rp. " + df.getInfakYatimBalance());
            txtTglPemasukkan.setText(pemberi.getTanggal());
            txtTglPengeluaran.setText(penerima.getTanggal());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
