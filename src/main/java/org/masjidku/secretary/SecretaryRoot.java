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

package org.masjidku.secretary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import org.masjidku.MainApp;

public class SecretaryRoot {

    public ToggleGroup groupButton;
    private MainApp mainApp;
    private String username = null;
    
    
    public void setMainApp(MainApp mainApp, String username) {
        this.username = username;
        this.mainApp = mainApp;
    }


    public void homeAction(ActionEvent actionEvent) { mainApp.setSecretaryView(username); }

    public void profileAction(ActionEvent actionEvent) { }

    public void activityManage(ActionEvent actionEvent) { }

    public void aboutAction(ActionEvent actionEvent) { mainApp.showAbout(); }
}
