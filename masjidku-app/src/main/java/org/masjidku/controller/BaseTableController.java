/*
 * Copyright (c) 2026. Creative Commons Legal Code
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

package org.masjidku.controller;

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

public abstract class BaseTableController<T> extends ReadOnlyTableController<T> {

    @SuppressWarnings("unused")
    protected Stage dialogStage;

    protected abstract Button getBtnEdit();

    protected abstract Button getBtnRemove();

    protected abstract boolean checkIfExist(T item) throws SQLException;

    protected abstract void deleteItem(T item) throws SQLException;

    protected abstract void handleEdit(T item);

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

