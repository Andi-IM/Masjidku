package org.masjidku.admin;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.masjidku.MainApp;

public class AdminHome {
    @FXML
    public Text greeting;

    private MainApp mainApp;

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
