package org.masjidku.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.masjidku.MainApp;

import java.net.URL;
import java.util.ResourceBundle;

public class UserForm implements Initializable {
    ObservableList<String> list = FXCollections.observableArrayList();

    @FXML
    public TextField txtUserId;
    @FXML
    public TextField txtUserName;
    @FXML
    public ChoiceBox<String> pilJabatan;
    @FXML
    public CheckBox statusCheckBox;

    private MainApp mainApp;

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

    public void gotoList() { mainApp.showUser(); }

    public void onUserSubmitted() {
        if (formValidation()){
            String userid = txtUserId.getText();
            String username = txtUserName.getText();
            String jabatan = pilJabatan.getValue();
            String status = statusCheckBox.getText();
        }
    }

    private boolean formValidation() {
        if (txtUserId.isCache()){
            if (txtUserName.isCache()){
                return pilJabatan.getValue() != null;
            }
        }
        return false;
    }
}
