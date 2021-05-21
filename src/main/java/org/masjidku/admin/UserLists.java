package org.masjidku.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.masjidku.MainApp;
import org.masjidku.model.User;
import org.masjidku.model.UserDao;

import java.io.IOException;
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
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Log out user.
     */
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

        if(userTable.getSelectionModel().isEmpty()){
            btnEdit.setDisable(false);
            btnRemove.setDisable(false);
            btnReset.setDisable(false);
        } else {
            btnEdit.setDisable(true);
            btnReset.setDisable(true);
            btnRemove.setDisable(true);
        }
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
                userData.addAll(dao.getUsers());
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
                        dao.delete(selectedUser.getUserId());
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
                    if (dao.isUserExist(selectedUser.getUserId())){
                        dao.reset(selectedUser.getUserId());
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
     * Alert Builder
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
}
