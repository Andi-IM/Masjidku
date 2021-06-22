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

package org.masjidku.principal.report.accountant;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import org.masjidku.MainApp;
import org.masjidku.model.accounting.DaoFunctions;
import org.masjidku.model.accounting.tpa.TpaKeluar;
import org.masjidku.model.accounting.tpa.TpaKeluarDao;
import org.masjidku.model.accounting.tpa.TpaMasuk;
import org.masjidku.model.accounting.tpa.TpaMasukDao;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TpaReport implements Initializable {
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
    public void uangKeluar() {  }

    @FXML
    public void uangMasuk() {  }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TpaMasukDao tpamdao= new TpaMasukDao();
        TpaKeluarDao tpakdao = new TpaKeluarDao();
        DaoFunctions df = new DaoFunctions();

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
