package org.masjidku.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.masjidku.navigation.AppRouter;
import org.masjidku.util.AlertHelper;

import java.sql.SQLException;

public abstract class BaseTableController<T> extends ReadOnlyTableController<T> {

    @SuppressWarnings("unused")
    protected Stage dialogStage;

    @FXML
    protected Button btnEdit;

    @FXML
    protected Button btnRemove;


    protected abstract boolean checkIfExist(T item) throws SQLException;

    protected abstract void deleteItem(T item) throws SQLException;

    protected abstract void handleEdit(T item);

    @FXML
    public void onMouseClicked() {
        AlertHelper.handleTableSelection(getTableView(), btnEdit, btnRemove);
    }

    @FXML
    public void onEditListener() {
        T selectedItem = getTableView().getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            handleEdit(selectedItem);
        } else {
            AlertHelper.alertError(dialogStage, "Null Error", "Data tidak ditemukan!");
        }
    }

    @FXML
    public void onRemoveListener() {
        T selectedItem = getTableView().getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            try {
                if (checkIfExist(selectedItem)) {
                    getTableView().getItems().remove(selectedItem);
                    deleteItem(selectedItem);
                    AlertHelper.alertInfo(dialogStage, "Success", "Data dihapus!");
                } else {
                    AlertHelper.alertError(dialogStage, "SQL Error", "Data tidak ditemukan!");
                }
            } catch (Exception e) {
                getLogger().error("An error occurred", e);
            }
        }
    }
}
