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
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.masjidku.MainApp;
import org.masjidku.model.accounting.DaoFunctions;
import org.masjidku.model.accounting.anakyatim.AnakYatim;
import org.masjidku.model.accounting.anakyatim.AnakYatimDao;
import org.masjidku.model.accounting.anakyatim.DonasiAYatim;
import org.masjidku.model.accounting.anakyatim.DonasiAYatimDao;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccountantAnakyatim implements Initializable {

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
    public Button btnDaftarAnakYatim;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnakYatimDao ayDao = new AnakYatimDao();
        DonasiAYatimDao dayDao = new DonasiAYatimDao();
        DaoFunctions df = new DaoFunctions();

        try {
            if (ayDao.getConnection() && dayDao.getConnection() && df.getConnection()) {
                AnakYatim penerima = ayDao.getLastRecord();
                DonasiAYatim pemberi = dayDao.getLastRecord();

                txtPemasukanTerakhir.setText("Rp. " + penerima.getJumlah());
                txtPengeluaranTerakhir.setText("Rp. " + pemberi.getJumlah());
                txtTotalPemasukkan.setText("Rp. " + dayDao.getTotalIncome());
                txtTotalPengeluaran.setText("Rp. " + ayDao.getTotalOutcome());
                txtSaldo.setText("Rp. " + df.getInfakYatimBalance());
                txtTglPemasukkan.setText(pemberi.getTanggal());
                txtTglPengeluaran.setText(penerima.getTanggal());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }

    @FXML
    public void onKelolaDonasiAYatim() {  mainApp.showDonasiAYatim(); }

    @FXML
    public void onKelolaDanaAYatim() { mainApp.showDaftarAnakYatim(); }
}
