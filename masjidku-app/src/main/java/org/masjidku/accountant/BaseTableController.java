package org.masjidku.accountant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.masjidku.util.AlertHelper;
import org.slf4j.Logger;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public abstract class BaseTableController<T> implements Initializable {
    protected final ObservableList<T> tableData = FXCollections.observableArrayList();
    
    @SuppressWarnings("unused")
    protected Stage dialogStage;

    protected abstract Logger getLogger();
    protected abstract TableView<T> getTableView();
    protected abstract Button getBtnEdit();
    protected abstract Button getBtnRemove();
    protected abstract List<T> fetchAllData() throws SQLException;
    protected abstract boolean checkIfExist(T item) throws SQLException;
    protected abstract void deleteItem(T item) throws SQLException;
    protected abstract void setupTableColumns();
    protected abstract void handleEdit(T item);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTableColumns();
        AlertHelper.loadTableData(tableData, this::fetchAllData, getLogger());
        getTableView().setItems(tableData);
    }

    @FXML
    public void onMouseClicked() {
        AlertHelper.handleTableSelection(getTableView(), getBtnEdit(), getBtnRemove());
    }

    protected void onEditAction() {
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
            } catch (SQLException e) {
                getLogger().error("An error occurred", e);
            }
        }
    }
}
