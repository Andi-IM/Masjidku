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

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.synedra.validatorfx.Validator;
import org.masjidku.domain.repository.UserProfileRepository;
import org.masjidku.domain.repository.UserRepository;
import org.masjidku.domain.repository.impl.UserProfileRepositoryImpl;
import org.masjidku.domain.repository.impl.UserRepositoryImpl;
import org.masjidku.model.user.UserProfile;
import org.masjidku.navigation.AppRouter;

import static org.masjidku.util.AlertHelper.alertError;
import static org.masjidku.util.ValidationHelper.registerRequiredField;

public class EditProfileController {
    private static final String NEW_PASSWORD_KEY = "newPassword";
    private final Validator validator = new Validator();

    @FXML
    public Label lbUserID;
    @FXML
    public TextField txtUserName;
    @FXML
    public PasswordField txtOldPassword;
    @FXML
    public PasswordField txtNewPassword;
    @FXML
    public PasswordField txtConfirmPassword;
    @FXML
    public TextField txtNoTel;
    @FXML
    public TextField txtAlamat;

    private AppRouter mainApp;

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;

    @FXML
    public void initialize() {
        registerRequiredField(validator, txtUserName, "username", "Username harus diisi!");
        registerRequiredField(validator, txtNewPassword, NEW_PASSWORD_KEY, "Password baru harus diisi!");
        registerRequiredField(validator, txtAlamat, "alamat", "Alamat harus diisi!");
        registerRequiredField(validator, txtNoTel, "notel", "Nomor telepon harus diisi!");

        registerOldPasswordCheck();
        registerPasswordMatchCheck();
    }

    private void registerOldPasswordCheck() {
        validator.createCheck()
                .dependsOn("oldPassword", txtOldPassword.textProperty())
                .withMethod(c -> {
                    String val = c.get("oldPassword");
                    if (val == null || val.isBlank()) {
                        c.error("Password lama harus diisi!");
                        return;
                    }
                    String userId = lbUserID.getText();
                    if (userId == null || userId.isBlank()) {
                        return;
                    }
                    UserRepository dao = new UserRepositoryImpl();
                    String hashed = com.google.common.hash.Hashing.sha256()
                            .hashString(val, java.nio.charset.StandardCharsets.UTF_8)
                            .toString();
                    if (!dao.isUserExist(userId, hashed)) {
                        c.error("Password lama salah!");
                    }
                })
                .decorates(txtOldPassword);
    }

    private void registerPasswordMatchCheck() {
        validator.createCheck()
                .dependsOn(NEW_PASSWORD_KEY, txtNewPassword.textProperty())
                .dependsOn("confirmPassword", txtConfirmPassword.textProperty())
                .withMethod(c -> {
                    String pass = c.get(NEW_PASSWORD_KEY);
                    String confirm = c.get("confirmPassword");
                    if (confirm == null || confirm.isBlank()) {
                        c.error("Konfirmasi password harus diisi!");
                    } else if (pass != null && !pass.equals(confirm)) {
                        c.error("Password tidak sama!");
                    }
                })
                .decorates(txtConfirmPassword);
    }

    @FXML
    public void setMainApp(AppRouter mainApp, UserProfile profile) {
        this.mainApp = mainApp;
        setUser(profile);
    }

    private void setUser(UserProfile profile) {
        lbUserID.setText(profile.user().id());
        txtUserName.setText(profile.user().username());
        txtAlamat.setText(profile.alamat());
        txtNoTel.setText(profile.notelp());
    }

    @FXML
    public void clearForm() {
        txtUserName.clear();
        txtOldPassword.clear();
        txtNewPassword.clear();
        txtConfirmPassword.clear();
        txtAlamat.clear();
        txtNoTel.clear();
    }

    @FXML
    public void onUserSubmitted() {
        if (formValidation()) {
            String id = lbUserID.getText();
            String username = txtUserName.getText();
            String newPassword = txtNewPassword.getText();
            String notel = txtNoTel.getText();
            String alamat = txtAlamat.getText();

            UserRepository dao = new UserRepositoryImpl();
            UserProfileRepository profileDao = new UserProfileRepositoryImpl();

            if (dao.isUserExist(id)) {
                dao.update(id, username, newPassword);
                profileDao.update(new String[]{notel, alamat, id});
            }
            profileDao.update(new String[]{id, notel, alamat});
        } else {
            alertError(dialogStage, "Empty Form", "Salah satu form tidak boleh kosong!");
        }
    }

    private boolean formValidation() {
        return validator.validate();
    }

    @FXML
    public void onBackAction() {
        mainApp.showProfile();
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }
}

