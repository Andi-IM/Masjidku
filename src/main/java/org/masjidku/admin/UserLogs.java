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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.masjidku.MainApp;
import org.masjidku.model.session.Session;
import org.masjidku.model.session.SessionDao;
import org.masjidku.model.session.UserSession;
import org.masjidku.model.user.User;
import org.masjidku.model.user.UserDao;

import java.net.URL;
import java.sql.SQLException;
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

    private MainApp mainApp;
    private Session dao;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * The data as an observable list of Sessions.
     */
    private final ObservableList<UserSession> sessionData =
            FXCollections.observableArrayList();

    @FXML
    public void onMouseClicked() { }

    @FXML
    public void onResetListener() {
        dao = new Session();
        if (dao.getConnection()){
            dao.truncateData();
            mainApp.showUserLog();
        }
    }

    /**
     * get User Data from DAO.
     *
     * @return Observable List
     */
    private ObservableList<UserSession> getSessionData() {
        dao = new Session();
        if (dao.getConnection()){
            sessionData.addAll(dao.getAllSessions());
        }
        return sessionData;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        activityTable.setItems(getSessionData());
        userid.setCellValueFactory(new PropertyValueFactory<>("userid"));
        timestamp.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
    }

    public void onLogoutClick() { mainApp.onLogoutAction(); }
}
