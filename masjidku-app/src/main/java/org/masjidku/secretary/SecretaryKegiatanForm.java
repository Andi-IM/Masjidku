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
import net.synedra.validatorfx.Validator;
import org.masjidku.events.client.EventsClient;
import org.masjidku.events.client.model.Kegiatan;
import org.masjidku.navigation.AppRouter;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

import static org.masjidku.di.DiProvider.getAppComponent;

public class SecretaryKegiatanForm {
    private static final Logger log = LoggerFactory.getLogger(SecretaryKegiatanForm.class);
    private final EventsClient eventClient = ServiceProvider.get(EventsClient.class);

    private final Validator validator = new Validator();


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

    private final Stage dialogStage = new Stage();


    public void setMainApp(AppRouter mainApp, Kegiatan kegiatan) {
        operator = getAppComponent().getSessionManager().getCurrentUsername();
        this.mainApp = mainApp;
        this.kegiatan = kegiatan;
    }

    @FXML
    public void initialize() {
        validator.createCheck()
                .dependsOn("nama", txtNamaKegiatan.textProperty())
                .withMethod(c -> {
                    String val = c.get("nama");
                    if (val == null || val.isBlank()) c.error("Nama Kegiatan harus diisi!");
                })
                .decorates(txtNamaKegiatan);

        validator.createCheck()
                .dependsOn("tempat", txtTempat.textProperty())
                .withMethod(c -> {
                    String val = c.get("tempat");
                    if (val == null || val.isBlank()) c.error("Tempat harus diisi!");
                })
                .decorates(txtTempat);

        validator.createCheck()
                .dependsOn("waktu", txtWaktu.textProperty())
                .withMethod(c -> {
                    String val = c.get("waktu");
                    if (val == null || val.isBlank()) c.error("Waktu harus diisi!");
                })
                .decorates(txtWaktu);

        validator.createCheck()
                .dependsOn("tanggal", txtTanggal.valueProperty())
                .withMethod(c -> {
                    if (c.get("tanggal") == null) c.error("Tanggal harus dipilih!");
                })
                .decorates(txtTanggal);
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

            kegiatan = new Kegiatan(namaKegiatan, waktu.toString(), tanggal, tempat, operator);

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
        return validator.validate();
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




