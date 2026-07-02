package org.masjidku.principal.report.keuangan;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import org.masjidku.navigation.AppRouter;
import org.masjidku.accounting.client.service.AccountingClient;
import org.masjidku.util.ServiceProvider;

public abstract class BaseKeuanganSummaryReport extends org.masjidku.controller.BaseAppController implements Initializable {
    protected final AccountingClient client = ServiceProvider.get(AccountingClient.class);

    @FXML public Text txtPemasukanTerakhir;
    @FXML public Text txtPengeluaranTerakhir;
    @FXML public Text txtTglPemasukkan;
    @FXML public Text txtTotalPemasukkan;
    @FXML public Text txtTotalPengeluaran;
    @FXML public Text txtSaldo;
    @FXML public Text txtTglPengeluaran;
    @FXML public void gotoHome() { mainApp.showData(); }
}
