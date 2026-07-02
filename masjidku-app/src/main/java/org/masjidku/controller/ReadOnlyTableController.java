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
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import org.masjidku.util.AlertHelper;
import org.slf4j.Logger;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Base controller for read-only table views. 
 * Provides structural boilerplate for fetching and rendering data without mutability operations.
 * 
 * @param <T> Model type displayed in the table.
 */
public abstract class ReadOnlyTableController<T> implements Initializable {
    protected final ObservableList<T> tableData = FXCollections.observableArrayList();

    protected abstract Logger getLogger();

    protected abstract TableView<T> getTableView();

    protected abstract List<T> fetchAllData() throws SQLException;

    protected abstract void setupTableColumns();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTableColumns();
        AlertHelper.loadTableData(tableData, this::fetchAllData, getLogger());
        getTableView().setItems(tableData);
    }
}
