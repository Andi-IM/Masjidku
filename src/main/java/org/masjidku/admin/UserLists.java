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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.masjidku.MainApp;
import org.masjidku.model.user.User;
import org.masjidku.model.user.UserDao;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserLists implements Initializable {

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
    private MainApp mainApp;

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
    public UserLists(){}

    /**
     * Is called by the main application to give a reference back to itself
     * @param mainApp reference to main application
     */
    public void setMainApp(MainApp mainApp) {
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
        UserDao dao = new UserDao();
        if (dao.getConnection()){
            try {
                userData.addAll(dao.getAll());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userData;
    }

    /**
     * Remove the selected user.
     */
    @FXML
    public void onRemoveListener() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null){
            UserDao dao = new UserDao();
            if (dao.getConnection()){
                try {
                    if (dao.isUserExist(selectedUser.getUserId())){
                        userTable.getItems().remove(selectedUser);
                        dao.delete(selectedUser.getUserId());
                        alertInfo("Success", "User dihapus!");
                    } else {
                        alertError("SQL Error", "User tidak ditemukan!");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                alertError("Offline", "Database tidak terhubung!");
            }
        }
    }

    /**
     * Reset the user specified password.
     */
    @FXML
    public void onResetListener() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null){
            UserDao dao = new UserDao();
            if (dao.getConnection()){
                try{
                    if (dao.isUserExist(selectedUser.getUserId())) {
                        if (dao.isReset(selectedUser.getUserId())) {
                            dao.reset(selectedUser.getUserId());
                        } else {
                            alertError("User Error", "User telah melakukan reset password!");
                        }
                    } else {
                        alertError("SQL Error","User tidak ditemukan!");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            alertError("Offline","Database tidak terhubung!");
        }
    }

    /**
     * Edit User
     */
    @FXML
    public void editListener() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null){
            mainApp.showUserEditScene(selectedUser);
        } else {
            alertError("Null Error", "User tidak ditemukan!");
        }
    }

    /**
     * Alert Error Builder
     * @param header header message
     * @param content content message
     */
    @SuppressWarnings("SameParameterValue")
    private void alertError(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Prompt");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }

    /**
     * Alert Info Builder
     * @param header header message
     * @param content content message
     */
    @SuppressWarnings("SameParameterValue")
    private void alertInfo(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(dialogStage);
        alert.setTitle("Prompt");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }

    @FXML
    public void onMouseClicked() {
        if(userTable.getSelectionModel().isEmpty()){
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
