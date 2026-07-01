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

package org.masjidku.principal.report.kegiatan;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.masjidku.controller.ReadOnlyTableController;
import org.masjidku.events.client.EventsClient;
import org.masjidku.events.client.model.TamuKegiatan;
import org.masjidku.navigation.AppRouter;
import org.masjidku.util.ServiceProvider;
import org.masjidku.util.TableHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ListUndangan extends ReadOnlyTableController<TamuKegiatan> {
    private static final Logger log = LoggerFactory.getLogger(ListUndangan.class);
    private final EventsClient dao = ServiceProvider.get(EventsClient.class);

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
    @FXML
    public TableColumn<TamuKegiatan, String> colOperator;

    private AppRouter mainApp;

    public void setMainApp(AppRouter mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    protected void setupTableColumns() {
        TableHelper.setupUndanganColumns(colNama, colAlamat, colKeterangan, colKegiatan, colNotelp, colOperator);
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @FXML
    public void showReport() {
        org.masjidku.di.DiProvider.getAppComponent().getReportService().showReport("/org/masjidku/report/list_undangan.jrxml");
    }

    @FXML
    public void gotoHome() {
        mainApp.showKegiatanOverview();
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
    protected List<TamuKegiatan> fetchAllData() throws java.sql.SQLException {
        return dao.getAllUndangan();
    }
}
