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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.masjidku.MainApp;
import org.masjidku.model.accounting.anakyatim.AnakYatimDao;
import org.masjidku.model.accounting.anakyatim.DonasiAYatimDao;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountantAnakyatim implements Initializable {
    @FXML
    public Button btnDaftarAnakYatim;
    @FXML
    public Button btnDaftarDonasiAnakYatim;
    @FXML
    public Text txtPemasukanTerakhir;
    @FXML
    public Text txtPengeluaranTerakhir;
    @FXML
    public Text txtTerakhirDiubah;

    private MainApp mainApp;
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnakYatimDao ayDao = new AnakYatimDao();
        DonasiAYatimDao dayDao = new DonasiAYatimDao();


    }

    public void onLogoutClick(MouseEvent mouseEvent) {
    }

    public void daftarDonasiAyatim(ActionEvent actionEvent) {
    }

    public void daftarAnakYatim(ActionEvent actionEvent) {
    }
}
