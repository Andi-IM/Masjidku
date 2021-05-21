package org.masjidku.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.masjidku.MainApp;
import org.masjidku.model.User;
import org.masjidku.model.UserDao;

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

    private MainApp mainApp;

    @SuppressWarnings("unused")
    private Stage dialogStage;
    private User user;

    public void setUser(User user) {
        this.user = user;

        txtUserId.setText(user.getUserId());
        txtUserName.setText(user.getUsername());
        pilJabatan.getSelectionModel().select(user.getJabatan().toString());
        statusCheckBox.setSelected(user.getStatus().equals("Aktif"));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private void loadData(){
        list.removeAll();
        String ketua = "ketua";
        String sekretaris = "sekretaris";
        String bendahara = "bendahara";
        list.addAll(ketua, sekretaris, bendahara);
        pilJabatan.getItems().addAll(list);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
    }

    @FXML
    public void onLogoutClick() { }

    @FXML
    public void onCheckboxAction() {
        if (statusCheckBox.isSelected()){
            statusCheckBox.setText("Aktif");
        } else statusCheckBox.setText("Nonaktif");
    }

    @FXML
    public void clearForm() {
        txtUserId.clear();
        txtUserName.clear();
        statusCheckBox.setSelected(false);
    }

    @FXML
    public void gotoList() { mainApp.showUser(); }

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
                   }
                   else {
                       dao.create(userid, username, jabatan, status);
                       alertInfo("Success", "User ditambahkan!");
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

    private boolean formValidation() {
        if (!txtUserId.getText().isBlank()){
            if (!txtUserName.getText().isBlank()){
                return !pilJabatan.getValue().isBlank();
            }
        }
        return false;
    }

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
