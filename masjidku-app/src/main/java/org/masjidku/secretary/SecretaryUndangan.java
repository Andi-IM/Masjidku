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

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.masjidku.controller.BaseTableController;
import org.masjidku.events.client.EventsClient;
import org.masjidku.events.client.model.TamuKegiatan;
import org.masjidku.navigation.AppRouter;
import org.masjidku.util.ServiceProvider;
import org.masjidku.util.TableHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class SecretaryUndangan extends BaseTableController<TamuKegiatan> {
    private static final Logger log = LoggerFactory.getLogger(SecretaryUndangan.class);

    @FXML
    public Button btnEdit;
    @FXML
    public Button btnRemove;
    @FXML
    public TableView<TamuKegiatan> tblUndangan;
    @FXML
    public TableColumn<String, String> colNomor;
    @FXML
    public TableColumn<TamuKegiatan, String> colNama;
    @FXML

    public TableColumn<TamuKegiatan, String> colAlamat;
    @FXML
    public TableColumn<TamuKegiatan, String> colKeterangan;
    @FXML
    public TableColumn<TamuKegiatan, String> colKegiatan;
    @FXML
    public TableColumn<TamuKegiatan, String> colNotelp;

    private AppRouter mainApp;
    private final EventsClient eventClient;

    public SecretaryUndangan() {
        eventClient = ServiceProvider.get(EventsClient.class);
    }

    public void setMainApp(AppRouter mainApp) {
        this.mainApp = mainApp;
    }


    @Override
    protected void setupTableColumns() {
        TableHelper.setupUndanganColumns(colNama, colAlamat, colKeterangan, colKegiatan, colNotelp, null);
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }


    @FXML
    public void tamuListener() {
        TamuKegiatan temp = new TamuKegiatan();
        mainApp.showUndanganEditForm(temp);
    }


    @Override
    protected org.slf4j.Logger getLogger() {
        return log;
    }

    @Override
    protected TableView<TamuKegiatan> getTableView() {
        return tblUndangan;
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
    protected ObservableList<TamuKegiatan> fetchAllData() throws java.sql.SQLException {
        return FXCollections.observableArrayList(eventClient.getAllUndangan());
    }

    @Override
    protected boolean checkIfExist(TamuKegiatan item) throws java.sql.SQLException {
        return eventClient.isUndanganExist(item.idUndangan());
    }

    @Override
    protected void deleteItem(TamuKegiatan item) throws java.sql.SQLException {
        eventClient.delete(item);
    }

    @Override
    protected void handleEdit(TamuKegiatan item) {
        if (item != null) {
            mainApp.showUndanganEditForm(item);
        }
    }

    @FXML
    public void onEditListener() {
        super.onEditAction();
    }
}




