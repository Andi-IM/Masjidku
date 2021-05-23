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
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.masjidku.MainApp;
import org.masjidku.model.User.User;
import org.masjidku.model.User.UserDao;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserForm implements Initializable {
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
    private MainApp mainApp;

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;

    private final boolean okClicked = false;

    // setting the field
    public void setUser(User user) {

        txtUserId.setText(user.getUserId());
        txtUserName.setText(user.getUsername());
        pilJabatan.getSelectionModel().select(user.getJabatan().toString);
        statusCheckBox.setSelected(user.getStatus() != null && user.getStatus().equals("Aktif"));
    }

    /**
     * Is called by the main application to give a reference back to itself
     * @param mainApp the main application reference
     */
    public void setMainApp(MainApp mainApp) {
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
    }

    /**
     * Log out user.
     */
    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }

    /**
     * Change the checkbox state
     */
    @FXML
    public void onCheckboxAction() {
        if (statusCheckBox.isSelected()){
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
        if (formValidation()){
            String userid = txtUserId.getText();
            String username = txtUserName.getText();
            String jabatan = pilJabatan.getValue();
            String status = statusCheckBox.getText();

            UserDao dao = new UserDao();
            if (dao.getConnection()){
               try {
                   if (dao.isUserExist(userid)){
                       dao.update(userid, jabatan, status);
                       alertInfo("Success", "User telah diperbarui!");
                       mainApp.showUser();
                   }
                   else {
                       dao.create(userid, username, jabatan, status);
                       alertInfo("Success", "User ditambahkan!");
                       mainApp.showUser();
                   }
                   mainApp.showUser();
               } catch (SQLException e){
                   System.out.println(e.getSQLState());
               }
            } else {
                alertError("Error", "Database belum dinyalakan!");
            }
        } else {
            alertError("Error", "Data belum lengkap!");
        }

    }

    /**
     * Validating user
     * @return fieldStatus
     */
    private boolean formValidation() {
        if (!txtUserId.getText().isBlank()){
            if (!txtUserName.getText().isBlank()){
                return !pilJabatan.getValue().isBlank();
            }
        }
        return false;
    }

    /**
     * Alert Error Builder
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
