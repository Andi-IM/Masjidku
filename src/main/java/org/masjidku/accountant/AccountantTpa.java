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

import java.net.URL;
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
    public void onKelolaDonasiTpa() { }

    @FXML
    public void onKelolaTpa() { }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       //  opDao = new OperationalDao();
       // DonasiOperationalDao doDao = new DonasiOperationalDao();
       // DaoFunctions df = new DaoFunctions();

//        try {
//            if (opDao.getConnection() && doDao.getConnection() && df.getConnection()) {
//                Operasional penerima = opDao.getLastRecord();
//                DonasiOperasional pemberi = doDao.getLastRecord();
//
//                txtPemasukanTerakhir.setText("Rp. " + penerima.getJumlah());
//                txtPengeluaranTerakhir.setText("Rp. " + pemberi.getJumlah());
//                txtTotalPemasukkan.setText("Rp. " + opDao.getTotalIncome());
//                txtTotalPengeluaran.setText("Rp. " + doDao.getTotalOutcome());
//                txtSaldo.setText("Rp. " + df.getInfakYatimBalance());
//                txtTglPemasukkan.setText(pemberi.getTanggal());
//                txtTglPengeluaran.setText(penerima.getTanggal());
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
