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
import org.masjidku.accounting.client.model.tpa.TpaKeluar;
import org.masjidku.accounting.client.model.tpa.TpaMasuk;
import org.masjidku.accounting.client.service.AccountingClient;
import org.masjidku.navigation.AppRouter;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TpaReport extends BaseKeuanganSummaryReport {
    private static final Logger log = LoggerFactory.getLogger(TpaReport.class);

    @FXML
    public void uangKeluar() {
        mainApp.showTpaKeluar();
    }

    @FXML
    public void uangMasuk() {
        mainApp.showTpaMasuk();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            TpaKeluar penerima = client.getLastTpaKeluar();
            TpaMasuk pemberi = client.getLastTpaMasuk();

            txtPemasukanTerakhir.setText("Rp. " + pemberi.jumlah());
            txtPengeluaranTerakhir.setText("Rp. " + penerima.jumlah());
            txtTotalPemasukkan.setText("Rp. " + client.getTotalTpaMasuk());
            txtTotalPengeluaran.setText("Rp. " + client.getTotalTpaKeluar());
            txtSaldo.setText("Rp. " + client.getTpaBalance());
            txtTglPemasukkan.setText(pemberi.tanggal().toString());
            txtTglPengeluaran.setText(penerima.tanggal().toString());

        } catch (Exception e) {
            log.error("An error occurred", e);
        }
    }
}


