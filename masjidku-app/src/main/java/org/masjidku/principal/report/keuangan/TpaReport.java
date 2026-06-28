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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.masjidku.util.ServiceProvider;
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

public class TpaReport implements Initializable {
    private static final Logger log = LoggerFactory.getLogger(TpaReport.class);
    private final AccountingFunctionsService df = ServiceProvider.get(AccountingFunctionsService.class);
    private final TpaKeluarService tpakdao = ServiceProvider.get(TpaKeluarService.class);
    private final TpaMasukService tpamdao = ServiceProvider.get(TpaMasukService.class);
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
    public void uangKeluar() { mainApp.showTpaKeluar(); }

    @FXML
    public void uangMasuk() { mainApp.showTpaMasuk(); }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            TpaKeluar penerima = tpakdao.getLastRecord();
            TpaMasuk pemberi = tpamdao.getLastRecord();

            txtPemasukanTerakhir.setText("Rp. " + pemberi.getJumlah());
            txtPengeluaranTerakhir.setText("Rp. " + penerima.getJumlah());
            txtTotalPemasukkan.setText("Rp. " + tpamdao.getTotalIncome());
            txtTotalPengeluaran.setText("Rp. " + tpakdao.getTotalOutcome());
            txtSaldo.setText("Rp. " + df.getTpaBalance());
            txtTglPemasukkan.setText(pemberi.getTanggal());
            txtTglPengeluaran.setText(penerima.getTanggal());

        } catch (SQLException e) {
            log.error("An error occurred", e);
        }
    }

    @FXML
    public void gotoHome() { mainApp.showData(); }
}

