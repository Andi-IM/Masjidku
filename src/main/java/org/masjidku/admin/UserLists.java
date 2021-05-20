package org.masjidku.admin;

import javafx.fxml.FXML;
import org.masjidku.MainApp;

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
