package org.masjidku.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.masjidku.MainApp;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLController implements Initializable {

    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     * @param mainApp the context
     */
    public void setMainApp(MainApp mainApp){ this.mainApp = mainApp; }

    @FXML
    private Label label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        label.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".");
    }
}
