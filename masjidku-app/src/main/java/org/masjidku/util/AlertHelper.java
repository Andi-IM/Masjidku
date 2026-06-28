package org.masjidku.util;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.slf4j.Logger;

public class AlertHelper {

    public static void alertError(Stage dialogStage, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Prompt");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void alertInfo(Stage dialogStage, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(dialogStage);
        alert.setTitle("Prompt");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static <T> void handleTableSelection(javafx.scene.control.TableView<T> table, javafx.scene.control.Button btnEdit, javafx.scene.control.Button btnRemove) {
        if (table.getSelectionModel().isEmpty()) {
            if (btnEdit != null) btnEdit.setDisable(true);
            if (btnRemove != null) btnRemove.setDisable(true);
        } else {
            if (btnEdit != null) btnEdit.setDisable(false);
            if (btnRemove != null) btnRemove.setDisable(false);
        }
    }

    public static <T> void setupInflowColumns(javafx.scene.control.TableColumn<T, String> donatur, javafx.scene.control.TableColumn<T, String> jumlah, javafx.scene.control.TableColumn<T, String> tanggal) {
        if (donatur != null) donatur.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("donatur"));
        if (jumlah != null) jumlah.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("jumlah"));
        if (tanggal != null) tanggal.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("tanggal"));
    }

    public static <T> void setupOutflowColumns(javafx.scene.control.TableColumn<T, String> nama, javafx.scene.control.TableColumn<T, String> jumlah, javafx.scene.control.TableColumn<T, String> tanggal) {
        if (nama != null) nama.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("tujuan"));
        if (jumlah != null) jumlah.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("jumlah"));
        if (tanggal != null) tanggal.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("tanggal"));
    }

    public interface SQLDataSupplier<T> {
        java.util.List<T> get() throws java.sql.SQLException;
    }

    public static <T> void loadTableData(
            ObservableList<T> targetList,
            SQLDataSupplier<T> supplier, 
            Logger log) {
        try {
            targetList.addAll(supplier.get());
        } catch (java.sql.SQLException e) {
            if (log != null) log.error("An error occurred", e);
        }
    }
}
