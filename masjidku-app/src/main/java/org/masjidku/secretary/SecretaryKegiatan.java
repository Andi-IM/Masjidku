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
import org.masjidku.events.client.model.Kegiatan;
import org.masjidku.navigation.AppRouter;
import org.masjidku.util.ServiceProvider;
import org.masjidku.util.TableHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SecretaryKegiatan extends BaseTableController<Kegiatan> {
    private static final Logger log = LoggerFactory.getLogger(SecretaryKegiatan.class);

    @FXML
    public Button btnEdit;
    @FXML
    public Button btnRemove;
    @FXML
    public TableView<Kegiatan> tblKegiatan;
    @FXML
    public TableColumn<Kegiatan, String> colNomor;
    @FXML
    public TableColumn<Kegiatan, String> colNmKegiatan;
    @FXML
    public TableColumn<Kegiatan, String> colTempatKegiatan;
    @FXML
    public TableColumn<Kegiatan, String> colWaktuKegiatan;
    @FXML
    public TableColumn<Kegiatan, String> colTanggalKegiatan;

    private AppRouter mainApp;
    private final EventsClient eventClient;

    public SecretaryKegiatan() {
        this.eventClient = ServiceProvider.get(EventsClient.class);
    }

    public void setMainApp(AppRouter mainApp) {
        this.mainApp = mainApp;
    }


    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @FXML
    public void addListener() {
        Kegiatan temp = new Kegiatan();
        mainApp.showKegiatanEditform(temp);
    }


    /**
     * Remove the selected kegiatan.
     */
    @Override
    protected void setupTableColumns() {
        TableHelper.setupKegiatanColumns(colNomor, colNmKegiatan, colTempatKegiatan, colWaktuKegiatan, colTanggalKegiatan, null);
    }

    @Override
    protected org.slf4j.Logger getLogger() {
        return log;
    }

    @Override
    protected TableView<Kegiatan> getTableView() {
        return tblKegiatan;
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
    protected List<Kegiatan> fetchAllData() {
        return eventClient.getAllKegiatan();
    }

    @Override
    protected boolean checkIfExist(Kegiatan item) {
        return eventClient.isKegiatanExist(item.idKegiatan());
    }

    @Override
    protected void deleteItem(Kegiatan item) {
        eventClient.delete(item);
    }

    @Override
    protected void handleEdit(Kegiatan item) {
        if (item != null) {
            mainApp.showKegiatanEditform(item);
        }
    }

    @FXML
    public void onEditListener() {
        super.onEditAction();
    }
}
