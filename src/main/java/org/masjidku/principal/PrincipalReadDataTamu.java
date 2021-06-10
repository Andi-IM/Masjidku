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

package org.masjidku.principal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.masjidku.MainApp;

public class PrincipalReadDataTamu {
    @FXML
    public Text greeting;
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp, String username) {
        this.mainApp = mainApp;
        greeting.setText("Bapak "+username);
    }

    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }

    public void editListener(ActionEvent actionEvent) {
    }

    public void onResetListener(ActionEvent actionEvent) {
    }

    public void onRemoveListener(ActionEvent actionEvent) {
    }
}
