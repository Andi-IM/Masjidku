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

import javafx.event.ActionEvent;
import org.masjidku.MainApp;
import org.masjidku.model.user.User;

public class AccountantRoot {

    private MainApp mainApp;
    private User user = null;

    public void setMainApp(MainApp mainApp, User user) {
        this.user = user;
        this.mainApp = mainApp;
    }

    public void homeAction(ActionEvent actionEvent) { mainApp.setAccountantView(user); }

    public void profileAction(ActionEvent actionEvent) { }

    public void infakManage(ActionEvent actionEvent) { }

    public void aboutAction(ActionEvent actionEvent) { mainApp.showAbout(); }
}
