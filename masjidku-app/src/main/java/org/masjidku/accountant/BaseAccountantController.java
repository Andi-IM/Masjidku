package org.masjidku.accountant;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public abstract class BaseAccountantController implements Initializable {
    
    @FXML public Text txtPemasukanTerakhir;
    @FXML public Text txtPengeluaranTerakhir;
    @FXML public Text txtTglPemasukkan;
    @FXML public Text txtTotalPemasukkan;
    @FXML public Text txtTotalPengeluaran;
    @FXML public Text txtSaldo;
    @FXML public Text txtTglPengeluaran;

    /**
     * Method reusable untuk mengupdate seluruh label dashboard
     */
    protected void updateDashboardSummary(
            String nominalPemasukanTerakhir, String nominalPengeluaranTerakhir,
            String totalPemasukan, String totalPengeluaran,
            String saldoAkhir,
            String tglPemasukan, String tglPengeluaran
    ) {
        txtPemasukanTerakhir.setText("Rp. " + nominalPemasukanTerakhir);
        txtPengeluaranTerakhir.setText("Rp. " + nominalPengeluaranTerakhir);
        txtTotalPemasukkan.setText("Rp. " + totalPemasukan);
        txtTotalPengeluaran.setText("Rp. " + totalPengeluaran);
        txtSaldo.setText("Rp. " + saldoAkhir);
        txtTglPemasukkan.setText(tglPemasukan);
        txtTglPengeluaran.setText(tglPengeluaran);
    }
}
