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
import org.masjidku.model.accounting.DaoFunctions;
import org.masjidku.model.accounting.pembangunan.DonasiPembangunan;
import org.masjidku.model.accounting.pembangunan.DonasiPembangunanDao;
import org.masjidku.model.accounting.pembangunan.Pembangunan;
import org.masjidku.model.accounting.pembangunan.PembangunanDao;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PembangunanReport implements Initializable {
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
    public void uangMasuk() { mainApp.showPembangunanMasuk(); }

    @FXML
    public void uangKeluar() { mainApp.showPembangunanKeluar(); }

    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PembangunanDao pbDao = new PembangunanDao();
        DonasiPembangunanDao dpDao = new DonasiPembangunanDao();
        DaoFunctions df = new DaoFunctions();

        try {
            if (pbDao.getConnection() && dpDao.getConnection() && df.getConnection()) {
                Pembangunan penerima = pbDao.getLastRecord();
                DonasiPembangunan pemberi = dpDao.getLastRecord();

                txtPemasukanTerakhir.setText("Rp. " + penerima.getJumlah());
                txtPengeluaranTerakhir.setText("Rp. " + pemberi.getJumlah());
                txtTotalPemasukkan.setText("Rp. " + pbDao.getTotalIncome());
                txtTotalPengeluaran.setText("Rp. " + dpDao.getTotalOutcome());
                txtSaldo.setText("Rp. " + df.getInfakYatimBalance());
                txtTglPemasukkan.setText(pemberi.getTanggal());
                txtTglPengeluaran.setText(penerima.getTanggal());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void gotoHome() { mainApp.showData(); }
}
