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

package org.masjidku.secretary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.masjidku.controller.BaseTableController;
import org.masjidku.events.client.EventsClient;
import org.masjidku.events.client.model.Tamu;
import org.masjidku.navigation.AppRouter;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecretaryTamu extends BaseTableController<Tamu> {
    private static final Logger log = LoggerFactory.getLogger(SecretaryTamu.class);
    private final EventsClient eventClient = ServiceProvider.get(EventsClient.class);

    @FXML
    public Button btnEdit;
    @FXML
    public Button btnRemove;
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

    public void setMainApp(AppRouter mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @FXML
    public void addListener() {
        Tamu temp = new Tamu();
        mainApp.showTamuEditForm(temp);
    }

    @Override
    protected void setupTableColumns() {
        org.masjidku.util.AlertHelper.setupTamuColumns(colNama, colAlamat, colNotelp);
    }

    @Override
    protected org.slf4j.Logger getLogger() {
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
    protected ObservableList<Tamu> fetchAllData() throws java.sql.SQLException {
        return FXCollections.observableArrayList(eventClient.getAllTamu());
    }

    @Override
    protected boolean checkIfExist(Tamu item) throws java.sql.SQLException {
        return eventClient.isTamuExist(item.idTamu());
    }

    @Override
    protected void deleteItem(Tamu item) throws java.sql.SQLException {
        eventClient.delete(item);
    }

    @Override
    protected void handleEdit(Tamu item) {
        if (item != null) {
            mainApp.showTamuEditForm(item);
        }
    }

    @FXML
    public void onEditListener() {
        super.onEditAction();
    }
}

