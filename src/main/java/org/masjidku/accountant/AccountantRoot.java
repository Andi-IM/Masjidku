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
import javafx.scene.control.ToggleGroup;
import org.masjidku.MainApp;

public class AccountantRoot {

    public ToggleGroup groupButton;
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void homeAction() { mainApp.setAccountantView(); }

    @FXML
    public void profileAction() { mainApp.showProfile(); }

    @FXML
    public void aboutAction() { mainApp.showAbout(); }

    @FXML
    public void ayatimAction() { mainApp.showAnakYatim(); }

    @FXML
    public void zakatAction() { mainApp.showZakat(); }

    @FXML
    public void pembangunanAction() { mainApp.showPembangunan(); }

    @FXML
    public void operasionalAction() { mainApp.showOperasional(); }

    @FXML
    public void tpaAction() { mainApp.showTpa(); }
}
