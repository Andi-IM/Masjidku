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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.masjidku.MainApp;
import org.masjidku.model.user.UserDao;
import org.masjidku.model.user.UserProfile;
import org.masjidku.model.user.UserProfileDao;

import java.sql.SQLException;

public class EditProfileController {

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

    private MainApp mainApp;

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;

    @FXML
    public void setMainApp(MainApp mainApp, UserProfile profile) {
        this.mainApp = mainApp;
        setUser(profile);
    }

    private void setUser(UserProfile profile) {
        lbUserID.setText(profile.getUser().getUserId());
        txtUserName.setText(profile.getUser().getUsername());
        txtAlamat.setText(profile.getAlamat());
        txtNoTel.setText(profile.getNotelp());
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
        if (formValidation()){
            String id = lbUserID.getText();
            String username = txtUserName.getText();
            String newPassword = txtNewPassword.getText();
            String notel = txtNoTel.getText();
            String alamat = txtAlamat.getText();

            UserDao dao = new UserDao();
            UserProfileDao profileDao = new UserProfileDao();

            try {
                if (dao.getConnection() && profileDao.getConnection()){
                    if (dao.isUserExist(id)){
                        dao.update(id, username, newPassword);

                        profileDao.update(new String[]{notel, alamat, id});
                    }
                    profileDao.update(new String[]{id, notel, alamat});
                } else {
                    alertError("DB Error", "Database belum dinyalakan!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            alertError("Empty Form","Salah satu form tidak boleh kosong!");
        }
    }

    private boolean formValidation() {
        if (!txtUserName.getText().isBlank() && !txtOldPassword.getText().isBlank()) {
            if (!txtNewPassword.getText().isBlank() && txtConfirmPassword.getText().isBlank()) {
                if (txtNewPassword.getText().equals(txtConfirmPassword.getText())) {
                    return !txtAlamat.getText().isBlank() && !txtNoTel.getText().isBlank();
                } else {
                    alertError("Error","Password tidak sama!");
                }
            }
        }
        return false;
    }

    @FXML
    public void onBackAction() {
        mainApp.showProfile();
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
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
