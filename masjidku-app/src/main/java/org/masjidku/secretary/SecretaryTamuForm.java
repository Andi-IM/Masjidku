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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.synedra.validatorfx.Validator;
import org.masjidku.events.client.EventsClient;
import org.masjidku.events.client.model.Tamu;
import org.masjidku.navigation.AppRouter;
import org.masjidku.util.Constants;
import org.masjidku.util.ServiceProvider;

public class SecretaryTamuForm {
    private final EventsClient eventsClient = ServiceProvider.get(EventsClient.class);
    private final Validator validator = new Validator();

    @FXML
    public TextField txtNomorTelp;
    @FXML
    public TextField txtNamaTamu;
    @FXML
    public TextArea txtAlamat;

    private Tamu tamu;
    private AppRouter mainApp;

    @SuppressWarnings("unused")
    private Stage dialogStage;
    private String operator;


    @FXML
    public void initialize() {
        validator.createCheck()
                .dependsOn("nama", txtNamaTamu.textProperty())
                .withMethod(c -> {
                    String val = c.get("nama");
                    if (val == null || val.isBlank()) c.error("Nama tamu harus diisi!");
                })
                .decorates(txtNamaTamu);

        validator.createCheck()
                .dependsOn("alamat", txtAlamat.textProperty())
                .withMethod(c -> {
                    String val = c.get("alamat");
                    if (val == null || val.isBlank()) c.error("Alamat harus diisi!");
                })
                .decorates(txtAlamat);

        validator.createCheck()
                .dependsOn("notelp", txtNomorTelp.textProperty())
                .withMethod(c -> {
                    String val = c.get("notelp");
                    if (val == null || val.isBlank()) c.error("Nomor telepon harus diisi!");
                })
                .decorates(txtNomorTelp);
    }

    public void setMainApp(AppRouter mainApp, Tamu tamu) {
        operator = org.masjidku.di.DiProvider.getAppComponent().getSessionManager().getCurrentUser().getUsername();
        this.mainApp = mainApp;
        this.tamu = tamu;

        if (tamu != null) {
            setTamu(tamu);
        }
    }

    private void setTamu(Tamu tamu) {
        txtNamaTamu.setText(tamu.nama());
        txtAlamat.setText(tamu.alamat());
        txtNomorTelp.setText(tamu.notelp());
    }

    @FXML
    public void onBackAction() {
        mainApp.showTamu();
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @FXML
    public void clearForm() {
        txtNamaTamu.clear();
        txtAlamat.clear();
        txtNomorTelp.clear();
    }

    @FXML
    public void onUserSubmitted() {
        if (formValidation()) {
            String namaTamu = txtNamaTamu.getText();
            String alamat = txtAlamat.getText();
            String noTelp = txtNomorTelp.getText();

            if (tamu == null) {
                tamu = new Tamu(null, namaTamu, alamat, noTelp, operator);
            } else {
                tamu = new Tamu(tamu.idTamu(), namaTamu, alamat, noTelp, operator);
            }

            if (eventsClient.isTamuExist(tamu.idTamu())) {
                eventsClient.update(tamu);
                org.masjidku.util.AlertHelper.alertInfo(dialogStage, Constants.SUCCESS, "Tamu telah diupdate");
            } else {
                eventsClient.save(tamu);
                org.masjidku.util.AlertHelper.alertInfo(dialogStage, Constants.SUCCESS, "Tamu telah disimpan");
            }
        } else {
            org.masjidku.util.AlertHelper.alertError(dialogStage, Constants.ERROR, "Data belum lengkap!");
        }
    }

    private boolean formValidation() {
        return validator.validate();
    }
}

