package org.masjidku.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.masjidku.MainApp;
import org.masjidku.model.User;

public class AdminHome {
    @FXML
    public Text greeting;

    private MainApp mainApp;
    private User user;

    @FXML
    public void handleLoginPage(ActionEvent actionEvent) {

    }

    public void setMainApp(MainApp mainApp, User user) {
        this.mainApp = mainApp;
        this.user = user;
        greeting.setText("Bapak "+user.getNama());
    }


    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }


}
