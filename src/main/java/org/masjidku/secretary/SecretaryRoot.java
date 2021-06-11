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

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import org.masjidku.MainApp;

public class SecretaryRoot {

    @FXML
    public ToggleGroup groupButton;
    private MainApp mainApp;
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void homeAction() { mainApp.setSecretaryView(); }

    @FXML
    public void profileAction() { mainApp.showProfile(); }

    @FXML
    public void activityManage() { mainApp.setKegiatan(); }

    @FXML
    public void visitorManage() { mainApp.showTamu(); }

    @FXML
    public void invitationManage() { mainApp.showUndangan(); }

    @FXML
    public void aboutAction() { mainApp.showAbout(); }
}
