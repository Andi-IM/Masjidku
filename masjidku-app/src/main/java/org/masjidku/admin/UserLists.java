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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.masjidku.model.user.User;
import org.masjidku.navigation.AppRouter;
import org.masjidku.service.UserService;
import org.masjidku.service.impl.UserServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class UserLists implements Initializable {
    private static final String USER_NOT_FOUND_MSG = "User tidak ditemukan!";

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, String> userid;

    @FXML
    private TableColumn<User, String> username;

    @FXML
    private TableColumn<User, String> jabatan;

    @FXML
    private TableColumn<User, String> status;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnRemove;

    //Reference to the main application.
    private AppRouter mainApp;

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;

    /**
     * The data as an observable list of Users.
     */
    private final ObservableList<User> userData =
            FXCollections.observableArrayList();

    /**
     * The Constructor.
     * The Constructor is called before the initialize() method.
     */
    private UserLists() {
    }

    /**
     * Is called by the main application to give a reference back to itself
     *
     * @param mainApp reference to main application
     */
    public void setMainApp(AppRouter mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Log out user.
     */
    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    /**
     * Opens a scene to add user.
     */
    @FXML
    public void addUserListener() {
        User tempUser = new User();
        mainApp.showUserEditScene(tempUser);
    }

    /**
     * Initialize the controller class. This method is automatically
     * called after the fxml file has been loaded.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userTable.setItems(getUserData());

        userid.setCellValueFactory(new PropertyValueFactory<>("userId"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        jabatan.setCellValueFactory(new PropertyValueFactory<>("jabatan"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    /**
     * get User Data from DAO.
     *
     * @return Observable List
     */
    private ObservableList<User> getUserData() {
        UserService dao = new UserServiceImpl();
        userData.addAll(dao.getAll());
        return userData;
    }

    /**
     * Remove the selected user.
     */
    @FXML
    public void onRemoveListener() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            UserService dao = new UserServiceImpl();
            if (dao.isUserExist(selectedUser.getUserId())) {
                userTable.getItems().remove(selectedUser);
                dao.delete(selectedUser.getUserId());
                org.masjidku.util.AlertHelper.alertInfo(dialogStage, "Success", "User dihapus!");
            } else {
                org.masjidku.util.AlertHelper.alertError(dialogStage, "SQL Error", USER_NOT_FOUND_MSG);
            }
        }
    }

    /**
     * Reset the user specified password.
     */
    @FXML
    public void onResetListener() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            return;
        }
        UserService dao = new UserServiceImpl();
        if (!dao.isUserExist(selectedUser.getUserId())) {
            org.masjidku.util.AlertHelper.alertError(dialogStage, "SQL Error", USER_NOT_FOUND_MSG);
            return;
        }
        if (dao.isReset(selectedUser.getUserId())) {
            dao.reset(selectedUser.getUserId());
        } else {
            org.masjidku.util.AlertHelper.alertError(dialogStage, "User Error", "User telah melakukan reset password!");
        }
    }

    /**
     * Edit User
     */
    @FXML
    public void onEditListener() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            mainApp.showUserEditScene(selectedUser);
        } else {
            org.masjidku.util.AlertHelper.alertError(dialogStage, "Null Error", USER_NOT_FOUND_MSG);
        }
    }


    @FXML
    public void onMouseClicked() {
        if (userTable.getSelectionModel().isEmpty()) {
            btnEdit.setDisable(true);
            btnRemove.setDisable(true);
            btnReset.setDisable(true);
        } else {
            btnEdit.setDisable(false);
            btnRemove.setDisable(false);
            btnReset.setDisable(false);
        }
    }
}

