package org.masjidku.accountant.anakyatim;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.masjidku.MainApp;
import org.masjidku.accountant.BaseTableController;
import org.masjidku.accounting.client.model.anakyatim.DonasiAYatim;
import org.masjidku.accounting.client.service.DonasiAYatimService;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class DonaturAnakYatim extends BaseTableController<DonasiAYatim> {
    private static final Logger log = LoggerFactory.getLogger(DonaturAnakYatim.class);
    private final DonasiAYatimService dao = ServiceProvider.get(DonasiAYatimService.class);

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

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
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
    protected List<DonasiAYatim> fetchAllData() throws SQLException {
        return dao.getAll();
    }

    @Override
    protected boolean checkIfExist(DonasiAYatim item) throws SQLException {
        return dao.isDonaturExist(item.getId());
    }

    @Override
    protected void deleteItem(DonasiAYatim item)  {
        dao.delete(item.getId());
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

