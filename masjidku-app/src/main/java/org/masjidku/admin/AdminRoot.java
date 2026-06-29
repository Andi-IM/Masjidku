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

package org.masjidku.admin;

import javafx.fxml.FXML;
import org.masjidku.navigation.AppRouter;

public class AdminRoot {
    @FXML
    public javafx.scene.control.ToggleGroup groupButton;

    private AppRouter mainApp;

    public void setMainApp(AppRouter mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void initialize() {
        org.masjidku.util.UIUtils.preventEmptyToggleSelection(groupButton);
    }

    @FXML
    public void homeAction() {
        mainApp.setAdminView();
    }

    @FXML
    public void profileAction(){ mainApp.showProfile(); }

    @FXML
    public void userManage(){ mainApp.showUser(); }

    @FXML
    public void aboutAction(){
        mainApp.showAbout();
    }

    @FXML
    public void userLog() { mainApp.showUserLog(); }
}

