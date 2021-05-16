package org.masjidku.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.masjidku.MainApp;
import org.masjidku.model.UserDao;

public class AdminRoot {
    private MainApp mainApp;

    @FXML
    public Button btn_login;
    @FXML
    public Button btn_home;
    @FXML
    public Button btn_about;

    @FXML
    public void handleLoginPage() {

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
