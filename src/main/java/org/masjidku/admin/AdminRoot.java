package org.masjidku.admin;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import org.masjidku.MainApp;

public class AdminRoot {

    private MainApp mainApp;
    private String username = null;
    @FXML
    public ToggleGroup sidebarButton;

    public void setMainApp(MainApp mainApp, String username) {
        this.username = username;
        this.mainApp = mainApp;
    }

    @FXML
    public void homeAction() {
        mainApp.setAdminView(username);
    }

    @FXML
    public void profileAction(){ }

    @FXML
    public void userManage(){ mainApp.showUser(); }

    @FXML
    public void aboutAction(){
        mainApp.showAbout();
    }
}
