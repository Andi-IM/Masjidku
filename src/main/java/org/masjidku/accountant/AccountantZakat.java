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
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import org.masjidku.MainApp;
import org.masjidku.model.accounting.DaoFunctions;
import org.masjidku.model.accounting.zakat.ZakatKeluar;
import org.masjidku.model.accounting.zakat.ZakatKeluarDao;
import org.masjidku.model.accounting.zakat.ZakatMasuk;
import org.masjidku.model.accounting.zakat.ZakatMasukDao;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccountantZakat implements Initializable {
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
    public void onKelolaDonasiZakat() { }

    @FXML
    public void onKelolaPenerimaZakat() { }

    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ZakatKeluarDao zkDao = new ZakatKeluarDao();
        ZakatMasukDao zmDao = new ZakatMasukDao();
        DaoFunctions df = new DaoFunctions();

        try {
            if (zkDao.getConnection() && zmDao.getConnection() && df.getConnection()) {
                ZakatKeluar penerima = zkDao.getLastRecord();
                ZakatMasuk pemberi = zmDao.getLastRecord();

                txtPemasukanTerakhir.setText("Rp. " + pemberi.getJumlah() );
                txtPengeluaranTerakhir.setText("Rp. " + penerima.getJumlah());
                txtTotalPemasukkan.setText("Rp. " + zmDao.getTotalIncome());
                txtTotalPengeluaran.setText("Rp. " + zkDao.gettotalOutcome());
                txtSaldo.setText("Rp. " + df.getInfakYatimBalance());
                txtTglPemasukkan.setText(pemberi.getTanggal());
                txtTglPengeluaran.setText(penerima.getTanggal());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
