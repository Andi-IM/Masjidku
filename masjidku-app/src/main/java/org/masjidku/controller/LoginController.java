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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.masjidku.auth.domain.repository.UserRepository;
import org.masjidku.auth.domain.repository.impl.UserRepositoryImpl;
import org.masjidku.auth.client.model.User;
import org.masjidku.navigation.AppRouter;

import java.nio.charset.StandardCharsets;

import net.synedra.validatorfx.Validator;
import static org.masjidku.util.ValidationHelper.registerRequiredField;

import static org.masjidku.util.AlertHelper.alertError;
import static org.masjidku.util.Constants.ACTIVE;


public class LoginController {
    private final Validator validator = new Validator();
    // Reference to the main application
    private AppRouter mainApp;

    @SuppressWarnings("unused")
    private Stage dialogStage;
    private UserRepository dao;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp the context
     */
    public void setMainApp(AppRouter mainApp) {
        this.mainApp = mainApp;
        dao = new UserRepositoryImpl();
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
        // Menambahkan listener agar pengguna dapat menekan 'Enter' untuk login
        javafx.event.EventHandler<javafx.scene.input.KeyEvent> enterKeyHandler = event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.ENTER) {
                handleLogin();
            }
        };

        if (txtUsername != null) {
            txtUsername.setOnKeyPressed(enterKeyHandler);
            registerRequiredField(validator, txtUsername, "username", "Username harus diisi!");
        }
        if (txtPassword != null) {
            txtPassword.setOnKeyPressed(enterKeyHandler);
            registerRequiredField(validator, txtPassword, "password", "Password harus diisi!");
        }
    }

    @FXML
    public void handleLogin() {
        if (validator.validate()) {
            validateLogin();
        }
    }

    private void validateLogin() {
        String username = txtUsername.getText();
        String password = Hashing
                .sha256()
                .hashString(txtPassword.getText(), StandardCharsets.UTF_8)
                .toString();

        if (dao.isUserExist(username, password)) {
            User user = dao.get(username);

            if (user.status().equals(ACTIVE)) {
                switch (user.getJabatan()) {
                    case ADMIN:
                        mainApp.recordSession(user);
                        mainApp.setAdminView();
                        break;
                    case KETUA:
                        mainApp.recordSession(user);
                        mainApp.setPrincipalView();
                        break;
                    case SEKRETARIS:
                        mainApp.recordSession(user);
                        mainApp.setSecretaryView();
                        break;
                    case BENDAHARA:
                        mainApp.recordSession(user);
                        mainApp.setAccountantView();
                        break;
                    default:
                        throw new IllegalArgumentException("Illegal Data Argument");
                }
            } else {
                alertError(dialogStage, "Gagal Masuk", "Mohon maaf, akun Anda tidak lagi aktif. " +
                        "Kontak Admin untuk informasi lebih lanjut.");
            }
        } else {
            alertError(dialogStage, "Gagal Masuk", "Periksa username dan password");
        }
    }


}

