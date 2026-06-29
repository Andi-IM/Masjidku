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

package org.masjidku.principal;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import org.masjidku.events.client.model.Tamu;
import org.masjidku.events.client.repository.TamuRepository;
import org.masjidku.events.client.service.TamuService;
import org.masjidku.navigation.AppRouter;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class PrincipalReadDataTamu extends org.masjidku.accountant.BaseTableController<Tamu> {
    private static final Logger log = LoggerFactory.getLogger(PrincipalReadDataTamu.class);

    @FXML
    public Button btnEdit;
    @FXML
    public Button btnReset;
    @FXML
    public Button btnRemove;

    @FXML
    public Text greeting;

    @FXML
    public TableView<Tamu> tblTamu;
    @FXML
    public TableColumn<Tamu, String> colNama;
    @FXML
    public TableColumn<Tamu, String> colAlamat;
    @FXML
    public TableColumn<Tamu, String> colNotelp;
    @FXML
    public TableColumn<Tamu, String> colNomor;

    private AppRouter mainApp;
    private final TamuService service = new TamuService(ServiceProvider.get(TamuRepository.class));

    public void setMainApp(AppRouter mainApp) {
        String username = org.masjidku.model.session.SessionManager.getInstance().getCurrentUser().getUsername();
        this.mainApp = mainApp;
        if (greeting != null) {
            greeting.setText("Bapak " + username);
        }
    }

    @Override
    protected void setupTableColumns() {
        org.masjidku.util.AlertHelper.setupTamuColumns(colNama, colAlamat, colNotelp);
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @Override
    protected void handleEdit(Tamu item) {
        if (mainApp != null) {
            mainApp.showTamuEditForm(item);
        }
    }

    @FXML
    public void onEditListener() {
        super.onEditAction();
    }

    @FXML
    public void onResetListener() {
        if (tblTamu != null) {
            tblTamu.getSelectionModel().clearSelection();
        }
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

    @Override
    protected TableView<Tamu> getTableView() {
        return tblTamu;
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
    protected List<Tamu> fetchAllData() throws SQLException {
        return service.getAll();
    }

    @Override
    protected boolean checkIfExist(Tamu item) throws SQLException {
        return service.isTamuExist(item.getIdTamu());
    }

    @Override
    protected void deleteItem(Tamu item) throws SQLException {
        service.delete(item.getIdTamu());
    }
}
