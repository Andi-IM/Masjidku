/*
 * Copyright (c) 2026. Creative Commons Legal Code
 */
package org.masjidku.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.masjidku.di.DiProvider;
import org.masjidku.navigation.AppRouter;

public abstract class BaseHomeController {

    @FXML
    public Text greeting;

    protected AppRouter mainApp;

    public void setMainApp(AppRouter mainApp) {
        String username = DiProvider.getAppComponent().getSessionManager().getCurrentUser().getUsername();
        this.mainApp = mainApp;
        if (greeting != null) {
            greeting.setText(getGreetingPrefix() + username);
        }
    }

    protected String getGreetingPrefix() {
        return "Bapak ";
    }

    @FXML
    public void onLogoutClick() {
        if (mainApp != null) {
            mainApp.onLogoutAction();
        }
    }
}
