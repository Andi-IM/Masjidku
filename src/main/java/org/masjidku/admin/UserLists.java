package org.masjidku.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import org.masjidku.MainApp;
import org.masjidku.model.User;

public class UserLists {
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @FXML
    public void addUserListener() { mainApp.createUser(); }
}
