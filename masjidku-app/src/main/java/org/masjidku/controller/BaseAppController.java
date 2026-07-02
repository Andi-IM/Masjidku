package org.masjidku.controller;

import javafx.fxml.FXML;
import org.masjidku.navigation.AppRouter;
import org.masjidku.navigation.AppRouterAware;

public abstract class BaseAppController implements AppRouterAware {
    protected AppRouter mainApp;

    @Override
    public void setMainApp(AppRouter mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void onLogoutClick() {
        if (mainApp != null) {
            mainApp.onLogoutAction();
        }
    }
}
