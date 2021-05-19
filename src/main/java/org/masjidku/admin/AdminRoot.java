package org.masjidku.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import org.masjidku.MainApp;

public class AdminRoot {

    private MainApp mainApp;

    @FXML
    public ToggleGroup sidebarButton;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void homeAction() {  }

    @FXML
    public void profileAction(){ }

    @FXML
    public void userManage(){ }

    @FXML
    public void aboutAction(){
        mainApp.showAbout();
    }
}
