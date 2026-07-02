/*
 * Copyright (c) 2026. Creative Commons Legal Code
 */
package org.masjidku.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.masjidku.navigation.AppRouter;
import org.masjidku.navigation.AppRouterAware;

import static org.masjidku.di.DiProvider.getAppComponent;

public abstract class BaseHomeController extends org.masjidku.controller.BaseAppController implements AppRouterAware {

    @FXML
    public Text greeting;

    @Override
    public void setMainApp(AppRouter mainApp) {
        String username = getAppComponent().getSessionManager().getCurrentUsername();
        this.mainApp = mainApp;
        if (greeting != null) {
            greeting.setText(getGreetingPrefix() + username);
        }
    }

    protected String getGreetingPrefix() {
        return "Bapak ";
    }
}
