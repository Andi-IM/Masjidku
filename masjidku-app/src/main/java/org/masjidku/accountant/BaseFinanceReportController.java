/*
 * Copyright (c) 2026. Creative Commons Universal CC0
 */

package org.masjidku.accountant;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import org.masjidku.controller.ReadOnlyTableController;
import org.masjidku.navigation.AppRouter;
import org.masjidku.reporting.client.service.ReportService;
import org.masjidku.util.ServiceProvider;

import java.io.File;

/**
 * Unified Base Finance Report Controller to eliminate code duplication across financial report controllers.
 */
public abstract class BaseFinanceReportController<T> extends ReadOnlyTableController<T> {
    // Inflow column fields
    @FXML
    protected TableColumn<T, String> donatur;

    // Outflow column fields
    @FXML
    protected TableColumn<T, String> nama;

    // Shared column fields
    @FXML
    protected TableColumn<T, String> jumlah;
    @FXML
    protected TableColumn<T, String> tanggal;
    @FXML
    protected TableColumn<T, String> operator;

    protected AppRouter mainApp;

    public void setMainApp(AppRouter mainApp) {
        this.mainApp = mainApp;
    }

    protected abstract String getReportTemplatePath();

    @FXML
    public void showReport() {
        org.masjidku.di.DiProvider.getAppComponent().getReportService().showReport(getReportTemplatePath());
    }

    @Override
    protected void setupTableColumns() {
        if (donatur != null && jumlah != null && tanggal != null) {
            org.masjidku.util.AlertHelper.setupInflowColumns(donatur, jumlah, tanggal);
        } else if (nama != null && jumlah != null && tanggal != null) {
            org.masjidku.util.AlertHelper.setupOutflowColumns(nama, jumlah, tanggal);
        }
        if (operator != null) {
            operator.setCellValueFactory(new PropertyValueFactory<>("operator"));
        }
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    protected void exportPdf(String templatePath, String defaultFileName) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Simpan Laporan PDF");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        fileChooser.setInitialFileName(defaultFileName);
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            org.masjidku.di.DiProvider.getAppComponent().getReportService().exportToPdf(templatePath, file.getAbsolutePath());
        }
    }
}
