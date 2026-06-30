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
package org.masjidku.accountant;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.masjidku.di.DiProvider;
import org.masjidku.navigation.AppRouter;

public class AccountantHome {

    
    @FXML
    private Text greeting;
    private AppRouter mainApp;

    public void setMainApp(AppRouter mainApp) {
        String username = DiProvider.getAppComponent().getSessionManager().getCurrentUser().getUsername();
        this.mainApp = mainApp;
        greeting.setText(username);
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }
}


