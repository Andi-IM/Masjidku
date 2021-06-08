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
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.masjidku.MainApp;
import org.masjidku.model.user.User;

public class SecretaryHome {
    @FXML
    public Button btnKegiatan;
    @FXML
    public Text greeting;
    private MainApp mainApp;
    private User user = null;

    public void setMainApp(MainApp mainApp, User user) {

        this.mainApp = mainApp;
        this.user = user;
        greeting.setText("Bapak "+user.getUsername());
    }

    @FXML
    public void onLogoutClick(MouseEvent mouseEvent) { mainApp.onLogoutAction(user.getUserId()); }

    @FXML
    public void onKelolaKegiatanClick(ActionEvent actionEvent) { }
}
