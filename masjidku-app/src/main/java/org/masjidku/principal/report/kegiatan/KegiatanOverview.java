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

package org.masjidku.principal.report.kegiatan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.masjidku.util.ServiceProvider;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import org.masjidku.MainApp;
import org.masjidku.events.client.model.Kegiatan;
import org.masjidku.events.client.service.KegiatanService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class KegiatanOverview implements Initializable {
    private static final Logger log = LoggerFactory.getLogger(KegiatanOverview.class);
    private final KegiatanService dao = ServiceProvider.get(KegiatanService.class);
    @FXML
    public Text txtKegiatanTerakhir;
    @FXML
    public Text txtTotalKegiatan;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void gotoHome() { mainApp.showData(); }

    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }

    @FXML
    public void kegiatanData() { mainApp.showKegiatanData(); }

    @FXML
    public void tamuData() { mainApp.showTamuData(); }

    @FXML
    public void undanganData() { mainApp.showUndanganData(); }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
                Kegiatan model = dao.getLastRecord();

                txtKegiatanTerakhir.setText(model.getNama());
                txtTotalKegiatan.setText(dao.getTotalKegiatan());
            
        } catch (SQLException e) {
            log.error("An error occurred", e);
        }
    }
}
