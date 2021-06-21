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

package org.masjidku.accountant.anakyatim;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.masjidku.MainApp;
import org.masjidku.model.accounting.anakyatim.DonasiAYatim;

import java.net.URL;
import java.util.ResourceBundle;

public class EditDonaturAnakYatim implements Initializable {
    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtJumlah;
    @FXML
    private DatePicker date;
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp, DonasiAYatim model) {
        this.mainApp = mainApp;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void clearForm() { }

    @FXML
    public void onSubmitted() { }

    @FXML
    public void gotoList() { mainApp.showDonasiAYatim(); }

    public void onLogoutClick() { mainApp.onLogoutAction(); }

}
