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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.synedra.validatorfx.Validator;
import org.masjidku.auth.client.AuthClient;
import org.masjidku.auth.client.model.User;
import org.masjidku.navigation.AppRouter;
import org.masjidku.util.ServiceProvider;

import java.net.URL;
import java.util.ResourceBundle;

import static org.masjidku.util.AlertHelper.alertError;
import static org.masjidku.util.AlertHelper.alertInfo;
import static org.masjidku.util.Constants.*;
import static org.masjidku.util.ValidationHelper.registerRequiredField;

public class UserForm implements Initializable {
    private final Validator validator = new Validator();
    final ObservableList<String> list = FXCollections.observableArrayList();

    @FXML
    public TextField txtUserId;
    @FXML
    public TextField txtUserName;
    @FXML
    public ChoiceBox<String> pilJabatan;
    @FXML
    public CheckBox statusCheckBox;

    // reference to main application
    private AppRouter mainApp;

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;

    // setting the field
    public void setUser(User user) {
        txtUserId.setText(user.id());
        txtUserName.setText(user.username());
        pilJabatan.getSelectionModel().select(user.jabatan());
        statusCheckBox.setSelected(user.status() != null && user.status().equals(ACTIVE));
    }

    /**
     * Is called by the main application to give a reference back to itself
     *
     * @param mainApp the main application reference
     */
    public void setMainApp(AppRouter mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.removeAll();
        String ketua = "ketua";
        String sekretaris = "sekretaris";
        String bendahara = "bendahara";
        list.addAll(ketua, sekretaris, bendahara);
        pilJabatan.getItems().addAll(list);

        registerRequiredField(validator, txtUserId, "userid", "User ID harus diisi!");
        registerRequiredField(validator, txtUserName, "username", "Nama User harus diisi!");
        validator.createCheck()
                .dependsOn("jabatan", pilJabatan.valueProperty())
                .withMethod(c -> {
                    String val = c.get("jabatan");
                    if (val == null || val.isBlank()) {
                        c.error("Jabatan harus dipilih!");
                    }
                })
                .decorates(pilJabatan);
    }

    /**
     * Log out user.
     */
    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    /**
     * Change the checkbox state
     */
    @FXML
    public void onCheckboxAction() {
        if (statusCheckBox.isSelected()) {
            statusCheckBox.setText("Aktif");
        } else statusCheckBox.setText("Nonaktif");
    }

    /**
     * Clear the form
     */
    @FXML
    public void clearForm() {
        txtUserId.clear();
        txtUserName.clear();
        statusCheckBox.setSelected(false);
    }

    /**
     * Navigate back to list.
     */
    @FXML
    public void gotoList() {
        mainApp.showUser();
    }

    /**
     * If User submit
     */
    @FXML
    public void onUserSubmitted() {
        if (formValidation()) {
            String userid = txtUserId.getText();
            String username = txtUserName.getText();
            String jabatan = pilJabatan.getValue();
            String status = statusCheckBox.getText();

            User user = new User(userid, username, jabatan, status, null, null);
            AuthClient dao = ServiceProvider.get(AuthClient.class);

            if (dao.isUserExist(userid)) {
                dao.updateUser(new String[]{user.jabatan(), user.status(), user.id()});
                alertInfo(dialogStage, SUCCESS, "User telah diperbarui!");
            } else {
                dao.saveUser(user);
                alertInfo(dialogStage, SUCCESS, "User ditambahkan!");
            }
            mainApp.showUser();
        } else {
            alertError(dialogStage, ERROR, "Data belum lengkap!");
        }

    }

    /**
     * Validating user
     *
     * @return fieldStatus
     */
    private boolean formValidation() {
        return validator.validate();
    }


}

