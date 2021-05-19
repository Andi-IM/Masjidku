package org.masjidku.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;
import org.masjidku.MainApp;
import org.masjidku.model.User;

public class AdminHome {
    @FXML
    public Text greeting;

    private MainApp mainApp;

    @FXML
    public void handleLoginPage() {

    }

    @FXML
    public void setMainApp(MainApp mainApp, String username) {
        this.mainApp = mainApp;
        greeting.setText("Bapak "+username);
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }
}
