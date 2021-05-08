package org.masjidku.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.masjidku.MainApp;


public class LoginController {
    // Reference to the main application
    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     * @param mainApp the context
     */
    public void setMainApp(MainApp mainApp){ this.mainApp = mainApp; }

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    private boolean buttonPressed = false;
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize(){ }

    @FXML
    public void handleLogin(){
        String un = username.getText();
        String ps = password.getText();
    }
}
