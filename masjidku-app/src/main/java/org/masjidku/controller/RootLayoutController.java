/*
 * Copyright (c) 2021. Creative Commons Legal Code
 *
 *                            CC0 1.0 Universal
 *
 *                                CREATIVE COMMONS CORPORATION IS NOT A LAW FIRM AND DOES NOT PROVIDE
 *                                LEGAL SERVICES. DISTRIBUTION OF THIS DOCUMENT DOES NOT CREATE AN
 *                                ATTORNEY-CLIENT RELATIONSHIP. CREATIVE COMMONS PROVIDES THIS
 *                                INFORMATION ON AN "AS-IS" BASIS. CREATIVE COMMONS MAKES NO WARRANTIES
 *                                REGARDING THE USE OF THIS DOCUMENT OR THE INFORMATION OR WORKS
 *                                PROVIDED HEREUNDER, AND DISCLAIMS LIABILITY FOR DAMAGES RESULTING FROM
 *                                THE USE OF THIS DOCUMENT OR THE INFORMATION OR WORKS PROVIDED
 *                                HEREUNDER.
 */

package org.masjidku.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import org.masjidku.navigation.AppRouter;

public class RootLayoutController {
    @FXML
    public ToggleButton btn_home;
    @FXML
    public ToggleButton btn_login;
    @FXML
    public ToggleButton btn_about;

    @FXML
    public ToggleGroup groupButton;

    @FXML
    public void initialize() {
        if (groupButton != null) {
            groupButton.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal == null && oldVal != null) {
                    javafx.application.Platform.runLater(() -> oldVal.setSelected(true));
                }
            });
        }
    }
    // Reference to the main application
    private AppRouter mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     * @param mainApp the context
     */
    public void setMainApp(AppRouter mainApp){ this.mainApp = mainApp; }

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
    public void handleLogin() {
        mainApp.showLogin();
    }

    /**
     * Opens about page.
     */
    @FXML
    public void handleAbout() {
        mainApp.showAbout();
    }

}

