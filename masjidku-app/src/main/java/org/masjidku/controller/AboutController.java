package org.masjidku.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import java.io.InputStream;
import java.util.Properties;

public class AboutController {

    @FXML
    private Text txtVersion;

    @FXML
    public void initialize() {
        String version = "1.0";
        try (InputStream input = AboutController.class.getResourceAsStream("/org/masjidku/version.properties")) {
            if (input != null) {
                Properties prop = new Properties();
                prop.load(input);
                version = prop.getProperty("version", "1.0");
            }
        } catch (Exception e) {
            // fallback to default
        }
        if (txtVersion != null) {
            txtVersion.setText("Aplikasi Desktop versi " + version + "\nMade by : ");
        }
    }
}
