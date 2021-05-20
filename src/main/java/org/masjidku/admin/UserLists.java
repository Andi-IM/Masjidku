package org.masjidku.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.masjidku.MainApp;
import org.masjidku.model.User;
import org.masjidku.model.UserDao;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserLists implements Initializable {
    private MainApp mainApp;

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

    ObservableList<User> list = FXCollections.observableArrayList();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @FXML
    public void addUserListener() { mainApp.createUser(); }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserDao dao = new UserDao();
        if (dao.getConnection()){
            try {
                list.addAll(dao.getUsers());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        userid.setCellValueFactory(new PropertyValueFactory<User, String>("userId"));
        username.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        jabatan.setCellValueFactory(new PropertyValueFactory<User, String>("jabatan"));
        status.setCellValueFactory(new PropertyValueFactory<User, String>("status"));

        userTable.setItems(list);
    }
}
