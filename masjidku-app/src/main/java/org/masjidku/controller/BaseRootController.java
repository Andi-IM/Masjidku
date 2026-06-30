/*
 * Copyright (c) 2026. Creative Commons Legal Code
 */
package org.masjidku.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import org.masjidku.navigation.AppRouter;
import org.masjidku.util.UIUtils;

import static org.masjidku.util.UIUtils.preventEmptyToggleSelection;

import org.masjidku.navigation.AppRouterAware;

public abstract class BaseRootController implements AppRouterAware {

    @FXML
    public ToggleGroup groupButton;

    protected AppRouter mainApp;

    public void setMainApp(AppRouter mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void initialize() {
        if (groupButton != null) {
            preventEmptyToggleSelection(groupButton);
        }
    }

    @FXML
    public void aboutAction() {
        if (mainApp != null) {
            mainApp.showAbout();
        }
    }

    @FXML
    public void profileAction() {
        if (mainApp != null) {
            mainApp.showProfile();
        }
    }

    @FXML
    public abstract void homeAction();
}
