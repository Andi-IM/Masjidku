package org.masjidku.accountant.anakyatim;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.masjidku.accounting.client.model.anakyatim.DonasiAYatim;
import org.masjidku.accounting.client.service.AccountingClient;
import org.masjidku.controller.BaseTableController;
import org.masjidku.navigation.AppRouter;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DonaturAnakYatim extends BaseTableController<DonasiAYatim> {
    private static final Logger log = LoggerFactory.getLogger(DonaturAnakYatim.class);
    private final AccountingClient client = ServiceProvider.get(AccountingClient.class);

    @FXML
    private TableView<DonasiAYatim> tblAYMasuk;
    @FXML
    private TableColumn<DonasiAYatim, String> donatur;
    @FXML
    private TableColumn<DonasiAYatim, String> jumlah;
    @FXML
    private TableColumn<DonasiAYatim, String> tanggal;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnRemove;

    private AppRouter mainApp;

    public void setMainApp(AppRouter mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

    @Override
    protected TableView<DonasiAYatim> getTableView() {
        return tblAYMasuk;
    }

    @Override
    protected Button getBtnEdit() {
        return btnEdit;
    }

    @Override
    protected Button getBtnRemove() {
        return btnRemove;
    }

    @Override
    protected List<DonasiAYatim> fetchAllData() {
        return client.getAllDonasiAYatim();
    }

    @Override
    protected boolean checkIfExist(DonasiAYatim item) {
        return client.isDonasiAYatimExist(item.id());
    }

    @Override
    protected void deleteItem(DonasiAYatim item) {
        client.delete(item);
    }

    @Override
    protected void setupTableColumns() {
        org.masjidku.util.AlertHelper.setupInflowColumns(donatur, jumlah, tanggal);
    }

    @Override
    protected void handleEdit(DonasiAYatim item) {
        mainApp.editDonaturAnakYatim(item);
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @FXML
    public void onCreateListener() {
        DonasiAYatim temp = new DonasiAYatim();
        mainApp.editDonaturAnakYatim(temp);
    }

    @FXML
    public void onEditListener() {
        super.onEditAction();
    }

    @FXML
    public void gotoHome() {
        mainApp.showAnakYatim();
    }
}


