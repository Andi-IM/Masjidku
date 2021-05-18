package org.masjidku.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.masjidku.MainApp;

public class RootLayoutController {
    @FXML
    public Button btn_home;
    @FXML
    public Button btn_login;
    @FXML
    public Button btn_about;

    // Reference to the main application
    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     * @param mainApp the context
     */
    public void setMainApp(MainApp mainApp){ this.mainApp = mainApp; }

    /**
     * Opens home page.
     */
    @FXML
    public void handleHome() {
        mainApp.showContent();
    }

    /**
     * Opens login page.
     */
    @FXML
    public void handleLogin() { mainApp.showLogin(); }

    /**
     * Opens about page.
     */
    @FXML
    public void handleAbout() {
        mainApp.showAbout();
    }

}
