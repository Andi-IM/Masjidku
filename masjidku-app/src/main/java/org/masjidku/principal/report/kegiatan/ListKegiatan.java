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
import javafx.stage.FileChooser;
import org.masjidku.controller.ReadOnlyTableController;
import org.masjidku.events.client.EventsClient;
import org.masjidku.events.client.model.Kegiatan;
import org.masjidku.navigation.AppRouter;
import org.masjidku.reporting.client.service.ReportService;
import org.masjidku.util.ServiceProvider;
import org.masjidku.util.TableHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

public class ListKegiatan extends ReadOnlyTableController<Kegiatan> {
    private static final Logger log = LoggerFactory.getLogger(ListKegiatan.class);
    private final EventsClient dao = ServiceProvider.get(EventsClient.class);

    @FXML
    public TableView<Kegiatan> tblKegiatan;
    @FXML
    public TableColumn<Kegiatan, String> colNomor;
    @FXML
    public TableColumn<Kegiatan, String> colNmKegiatan;
    @FXML
    public TableColumn<Kegiatan, String> colTanggalKegiatan;
    @FXML
    public TableColumn<Kegiatan, String> colTempatKegiatan;
    @FXML
    public TableColumn<Kegiatan, String> colWaktuKegiatan;
    @FXML
    public TableColumn<Kegiatan, String> colOperator;
    public AppRouter mainApp;

    public void setMainApp(AppRouter mainApp) {
        this.mainApp = mainApp;
    }


    @Override
    protected void setupTableColumns() {
        TableHelper.setupKegiatanColumns(colNomor, colNmKegiatan, colTempatKegiatan, colWaktuKegiatan, colTanggalKegiatan, colOperator);
    }

    @FXML
    public void printReport() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Simpan Laporan PDF");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        fileChooser.setInitialFileName("LaporanListKegiatan.pdf");
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            ServiceProvider.get(ReportService.class).exportToPdf("/org/masjidku/report/list_kegiatan.jrxml", file.getAbsolutePath());
        }
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
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
    protected TableView<Kegiatan> getTableView() {
        return tblKegiatan;
    }

    @Override
    protected List<Kegiatan> fetchAllData() throws java.sql.SQLException {
        return dao.getAllKegiatan();
    }
}
