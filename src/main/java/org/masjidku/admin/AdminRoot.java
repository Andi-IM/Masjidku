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
import javafx.scene.control.ToggleGroup;
import org.masjidku.MainApp;
import org.masjidku.model.user.User;

public class AdminRoot {

    private MainApp mainApp;
    private User user = null;

    public void setMainApp(MainApp mainApp, User user) {
        this.user = user;
        this.mainApp = mainApp;
    }

    @FXML
    public void homeAction() {
        mainApp.setAdminView(user);
    }

    @FXML
    public void profileAction(){ }

    @FXML
    public void userManage(){ mainApp.showUser(user.getUserId()); }

    @FXML
    public void aboutAction(){
        mainApp.showAbout();
    }
}
