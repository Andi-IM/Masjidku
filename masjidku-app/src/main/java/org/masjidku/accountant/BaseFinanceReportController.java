/*
 * Copyright (c) 2026. Creative Commons Universal CC0
 */

package org.masjidku.accountant;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.masjidku.navigation.AppRouter;
import org.masjidku.reporting.client.service.ReportService;
import org.masjidku.util.ServiceProvider;
import javafx.stage.FileChooser;
import java.io.File;

/**
 * Base Finance Report Controller to eliminate code duplication across financial report controllers.
 */
public abstract class BaseFinanceReportController<T> extends BaseTableController<T> {
    @FXML
    protected TableColumn<T, String> donatur;
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
        ServiceProvider.get(ReportService.class).showReport(getReportTemplatePath());
    }

    @Override
    protected void setupTableColumns() {
        org.masjidku.util.AlertHelper.setupInflowColumns(donatur, jumlah, tanggal);
        if (operator != null) {
            operator.setCellValueFactory(new PropertyValueFactory<>("operator"));
        }
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @Override
    protected Button getBtnEdit() {
        return null;
    }

    @Override
    protected Button getBtnRemove() {
        return null;
    }

    @Override
    protected boolean checkIfExist(T item) {
        return false;
    }

    @Override
    protected void deleteItem(T item) {
        // Read-only report view
    }

    @Override
    protected void handleEdit(T item) {
        // Read-only report view
    }

    protected void exportPdf(String templatePath, String defaultFileName) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Simpan Laporan PDF");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        fileChooser.setInitialFileName(defaultFileName);
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            ServiceProvider.get(ReportService.class).exportToPdf(templatePath, file.getAbsolutePath());
        }
    }
}
