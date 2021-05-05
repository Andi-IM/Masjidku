package org.masjidku.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.masjidku.MainApp;

public class RootLayoutController {
    // Reference to the main application
    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     * @param mainApp the context
     */
    public void setMainApp(MainApp mainApp){ this.mainApp = mainApp; }

    /**
     * Opens about page.
     */
    @FXML
    public void handleAbout() {
        mainApp.showAbout();
    }
}
