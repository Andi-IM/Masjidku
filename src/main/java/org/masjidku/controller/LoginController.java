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
import org.masjidku.util.UserSession;

import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Objects;


public class LoginController {
    // Reference to the main application
    private MainApp mainApp;
    private Stage dialogStage;
    private UserDao dao;
    private User user;

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
            String userId = dao.getUser(un, ps);
            if ( userId != null){
                user = dao.getUserData(userId);

                if (user.getStatus().equals("Aktif")){
                    switch (user.getJabatan()){
                        case "admin":
                            mainApp.adminAuth(user);
                            break;
                        case "ketua":
                            alertInfo("Dalam Perbaikan", "Mohon maaf status bagian ini dalam perbaikan");
                            break;
                        case "sektretaris":
                            alertInfo("Dalam Perbaikan", "Mohon maaf status bagian ini dalam perbaikan");
                            break;
                        case "bendahara":
                            alertInfo("Dalam Perbaikan", "Mohon maaf status bagian ini dalam perbaikan");
                            break;
                        default:
                            throw new IllegalArgumentException("Illegal Data Argument");
                    }

                } else {
                    alertError("Gagal Masuk", "Mohon maaf, akun Anda tidak lagi aktif. " +
                            "Kontak Admin untuk informasi lebih lanjut.");
                }
            } else {
                alertError("Gagal Masuk", "Periksa username dan password");
            }
        } catch (SQLException e){
            System.err.println(e);
        }
    }

    private void alertInfo(String header, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(dialogStage);
        alert.setTitle("Prompt");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }

    private void alertError(String header, String content){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Prompt");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
