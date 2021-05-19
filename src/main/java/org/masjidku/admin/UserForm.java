package org.masjidku.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.masjidku.MainApp;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserForm implements Initializable {
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

    public UserForm(){
        pilJabatan = new ChoiceBox<String>();
        pilJabatan.getItems().addAll("Pilih..","Ketua","Sekretaris","Bendahara");
    }

    @FXML
    private void handleButtonAction(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
}
