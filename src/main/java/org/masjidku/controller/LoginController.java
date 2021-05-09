package org.masjidku.controller;

import com.google.common.hash.Hashing;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.masjidku.MainApp;
import org.masjidku.model.User;
import org.masjidku.model.UserDao;

import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Objects;


public class LoginController {
    // Reference to the main application
    private MainApp mainApp;
    private Stage dialogStage;
    private UserDao dao;

    /**
     * Is called by the main application to give a reference back to itself.
     * @param mainApp the context
     */
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
        dao = new UserDao();
    }

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    private boolean buttonPressed = false;
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize(){ }

    @FXML
    public void handleLogin() {
        String un = username.getText();
        String ps = Hashing
                .sha256()
                .hashString(password.getText(), StandardCharsets.UTF_8)
                .toString();

        try {
            if (dao.getUser(un, ps)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initOwner(dialogStage);
                alert.setTitle("Prompt");
                alert.setHeaderText("Masuk berhasil!");
                alert.setContentText("Silakan lanjut");

                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Prompt");
                alert.setHeaderText("Gagal Masuk");
                alert.setContentText("Periksa username dan password");

                alert.showAndWait();
            }
        } catch (SQLException e){
            e.getMessage();
        }
    }

}
