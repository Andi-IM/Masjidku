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
package org.masjidku.admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.masjidku.auth.client.AuthClient;
import org.masjidku.auth.client.model.UserSession;
import org.masjidku.navigation.AppRouter;
import org.masjidku.util.ServiceProvider;

import java.net.URL;
import java.util.ResourceBundle;

public class UserLogs implements Initializable {
    @FXML
    public TableView<UserSession> activityTable;
    @FXML
    public TableColumn<UserSession, String> userid;
    @FXML
    public TableColumn<UserSession, String> timestamp;
    @FXML
    public TableColumn<UserSession, String> duration;
    private AppRouter mainApp;
    private AuthClient dao;

    public void setMainApp(AppRouter mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * The data as an observable list of Sessions.
     */
    private final ObservableList<UserSession> sessionData =
            FXCollections.observableArrayList();


    @FXML
    public void onResetListener() {
        dao = ServiceProvider.get(AuthClient.class);
        dao.truncateSessionData();
        mainApp.showUserLog();
    }

    /**
     * get User Data from DAO.
     *
     * @return Observable List
     */
    private ObservableList<UserSession> getSessionData() {
        dao = ServiceProvider.get(AuthClient.class);
        sessionData.addAll(dao.getAllSessions());
        return sessionData;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        activityTable.setItems(getSessionData());
        userid.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().userid()));
        timestamp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().timestamp()));
        duration.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().duration()));
    }

    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }
}

