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

package org.masjidku.controller;

import com.google.common.hash.Hashing;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.masjidku.MainApp;
import org.masjidku.model.user.User;
import org.masjidku.model.user.UserDao;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;


public class LoginController {
    // Reference to the main application
    private MainApp mainApp;

    @SuppressWarnings("unused")
    private Stage dialogStage;
    private UserDao dao;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp the context
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        dao = new UserDao();
    }

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {
    }

    @FXML
    public void handleLogin() {
        if (!txtUsername.getText().isBlank() && !txtPassword.getText().isBlank()) {
            validateLogin();
        } else {
            alertError("Alert!", "Mohon untuk menginput username dan passwordnya!");
        }
    }

    private void validateLogin() {
        String username = txtUsername.getText();
        @SuppressWarnings("UnstableApiUsage")
        String password = Hashing
                .sha256()
                .hashString(txtPassword.getText(), StandardCharsets.UTF_8)
                .toString();

        try {
            if (dao.getConnection()) {
                if (dao.isUserExist(username, password)) {
                    User user = dao.get(username);

                    if (user.getStatus().equals("Aktif")) {
                        switch (user.getJabatan()) {
                            case admin:
                                mainApp.recordSession(user);
                                mainApp.setAdminView();
                                break;
                            case ketua:
                                mainApp.recordSession(user);
                                mainApp.setPrincipalView();
                                break;
                            case sekretaris:
                                mainApp.recordSession(user);
                                mainApp.setSecretaryView();
                                break;
                            case bendahara:
                                mainApp.recordSession(user);
                                mainApp.setAccountantView();
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
            } else {
                alertError("Error DB", "Mohon Nyalakan Database!");
            }
        } catch (SQLException e) {
            System.err.println(e.getSQLState());
        }
    }

    private void alertError(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Prompt");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
