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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.masjidku.events.client.EventsClient;
import org.masjidku.events.client.model.Kegiatan;
import org.masjidku.navigation.AppRouter;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.time.LocalDate;

public class SecretaryKegiatanForm {
    private static final Logger log = LoggerFactory.getLogger(SecretaryKegiatanForm.class);


    @FXML
    private TextField txtNamaKegiatan;
    @FXML
    private DatePicker txtTanggal;
    @FXML
    private TextField txtTempat;
    @FXML
    private TextField txtWaktu;
    private Kegiatan kegiatan;
    private AppRouter mainApp;
    private String operator;

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;

    private final EventsClient eventClient;

    SecretaryKegiatanForm() {
        this.eventClient = ServiceProvider.get(EventsClient.class);
    }

    public void setMainApp(AppRouter mainApp, Kegiatan kegiatan) {
        String operator = org.masjidku.model.session.SessionManager.getInstance().getCurrentUser().getUsername();
        this.mainApp = mainApp;
        this.kegiatan = kegiatan;
        this.operator = operator;
    }

    public void setKegiatan(Kegiatan kegiatan) {
        txtNamaKegiatan.setText(kegiatan.nama());
        txtTempat.setText(kegiatan.tempat());
        txtTanggal.setValue(LocalDate.parse(kegiatan.tanggal()));
        txtWaktu.setText(kegiatan.waktu() != null ? kegiatan.waktu() : "");
    }

    @FXML
    public void clearForm() {
        txtNamaKegiatan.clear();
        txtWaktu.clear();
        txtTempat.clear();
        txtTanggal.setValue(null);
    }

    @FXML
    public void onUserSubmitted() {
        if (formValidation()) {
            String namaKegiatan = txtNamaKegiatan.getText();
            java.time.LocalTime waktu = java.time.LocalTime.parse(txtWaktu.getText());
            String tempat = txtTempat.getText();
            String tanggal = txtTanggal.getValue().toString();

            kegiatan = new Kegiatan(namaKegiatan, waktu, tanggal, tempat, operator);

            try {
                if (eventClient.isKegiatanExist(kegiatan.idKegiatan())) {
                    eventClient.update(kegiatan);
                    org.masjidku.util.AlertHelper.alertInfo(dialogStage, "Success", "Kegiatan telah diperbarui!");
                } else {
                    eventClient.save(kegiatan);
                    org.masjidku.util.AlertHelper.alertInfo(dialogStage, "Success", "Kegiatan telah ditambahkan!");
                }
                mainApp.showKegiatan();
            } catch (Exception e) {
                log.error("An error occurred", e);
            }

        } else {
            org.masjidku.util.AlertHelper.alertError(dialogStage, "Error", "Data belum lengkap!");
        }
    }

    private boolean formValidation() {
        if (!txtNamaKegiatan.getText().isBlank()) {
            if (!txtWaktu.getText().isBlank()) {
                if (!txtTempat.getText().isBlank()) {
                    return txtTanggal.getValue() != null;
                }
            }
        }
        return false;
    }

    @FXML
    public void gotoList() {
        mainApp.showKegiatan();
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }


}



