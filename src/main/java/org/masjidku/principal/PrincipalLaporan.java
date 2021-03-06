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

import javafx.fxml.FXML;
import org.masjidku.MainApp;

public class PrincipalLaporan {

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) { this.mainApp = mainApp; }

    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }

    @FXML
    public void printKegiatan() { mainApp.showKegiatanReport(); }

    @FXML
    public void printAnakYatim() { mainApp.showAnakYatimReport(); }

    @FXML
    public void printPembangunan() { mainApp.showPembangunanReport(); }

    @FXML
    public void printOperasinal() { mainApp.showOperasionalReport(); }

    @FXML
    public void printZakat() { mainApp.showZakatReport(); }

    @FXML
    public void printTpa() { mainApp.showTpaReport(); }
}
