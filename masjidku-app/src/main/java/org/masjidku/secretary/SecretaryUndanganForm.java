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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.masjidku.events.client.EventsClient;
import org.masjidku.events.client.model.Kegiatan;
import org.masjidku.events.client.model.Tamu;
import org.masjidku.events.client.model.TamuKegiatan;
import org.masjidku.navigation.AppRouter;
import org.masjidku.util.Constants;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SecretaryUndanganForm implements Initializable {
    private static final Logger log = LoggerFactory.getLogger(SecretaryUndanganForm.class);
    private final EventsClient eventsClient = ServiceProvider.get(EventsClient.class);

    @FXML
    public ChoiceBox<String> cbKegiatan;
    @FXML
    public ChoiceBox<String> cbTamu;
    @FXML
    public TextArea txtKeterangan;

    private AppRouter mainApp;

    private ObservableList<Tamu> listTamu;
    private ObservableList<Kegiatan> listKegiatan;

    @SuppressWarnings("unused")
    private Stage dialogStage;
    private String operator;

    private TamuKegiatan currentUndangan;

    public void setMainApp(AppRouter mainApp, TamuKegiatan undangan) {
        this.operator = org.masjidku.di.DiProvider.getAppComponent().getSessionManager().getCurrentUser().getUsername();
        this.mainApp = mainApp;

        if (undangan != null) {
            this.currentUndangan = undangan;
            setUndangan(undangan);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            cbTamu.getItems().clear();
            cbKegiatan.getItems().clear();

            listTamu = FXCollections.observableArrayList(eventsClient.getAllTamu());
            listKegiatan = FXCollections.observableArrayList(eventsClient.getAllKegiatan());

            List<String> tamuNames = listTamu.stream().map(Tamu::nama).toList();
            List<String> kegiatanNames = listKegiatan.stream().map(Kegiatan::nama).toList();

            cbTamu.getItems().addAll(tamuNames);
            cbKegiatan.getItems().addAll(kegiatanNames);
        } catch (Exception e) {
            log.error("An error occurred during initialization", e);
        }
    }

    private void setUndangan(TamuKegiatan undangan) {
        if (undangan.tamu() != null) {
            cbTamu.setValue(undangan.tamu().nama());
        }
        if (undangan.kegiatan() != null) {
            cbKegiatan.setValue(undangan.kegiatan().nama());
        }
        txtKeterangan.setText(undangan.keterangan());
    }

    @FXML
    public void clearForm() {
        cbTamu.getSelectionModel().clearSelection();
        cbKegiatan.getSelectionModel().clearSelection();
        txtKeterangan.clear();
        currentUndangan = null;
    }

    @FXML
    public void gotoUndangan() {
        mainApp.showUndangan();
    }

    @FXML
    public void onUserSubmitted() {
        String namaform = cbTamu.getValue();
        String kegiatanform = cbKegiatan.getValue();
        String keterangan = txtKeterangan.getText();

        if (namaform == null || kegiatanform == null) {
            org.masjidku.util.AlertHelper.alertError(dialogStage, Constants.ERROR, "Tamu dan Kegiatan harus dipilih!");
            return;
        }

        Tamu selectedTamu = listTamu.stream().filter(t -> t.nama().equals(namaform)).findFirst().orElse(null);
        Kegiatan selectedKegiatan = listKegiatan.stream().filter(k -> k.nama().equals(kegiatanform)).findFirst().orElse(null);

        if (selectedTamu == null || selectedKegiatan == null) {
            org.masjidku.util.AlertHelper.alertError(dialogStage, Constants.ERROR, "Tamu atau Kegiatan tidak valid!");
            return;
        }

        try {
            if (currentUndangan != null && eventsClient.isUndanganExist(currentUndangan.idUndangan())) {
                TamuKegiatan updatedUndangan = new TamuKegiatan(
                        currentUndangan.idUndangan(),
                        selectedTamu,
                        selectedKegiatan,
                        keterangan,
                        this.operator
                );
                eventsClient.update(updatedUndangan);
                org.masjidku.util.AlertHelper.alertInfo(dialogStage, Constants.SUCCESS, "Data telah diubah!");
            } else {
                TamuKegiatan newUndangan = new TamuKegiatan(
                        null, // ID generated by DB
                        selectedTamu,
                        selectedKegiatan,
                        keterangan,
                        this.operator
                );
                eventsClient.save(newUndangan);
                org.masjidku.util.AlertHelper.alertInfo(dialogStage, "Success", "Data telah ditambahkan!");
            }
        } catch (Exception e) {
            log.error("An error occurred during submission", e);
            org.masjidku.util.AlertHelper.alertError(dialogStage, "Error", "Gagal menyimpan data: " + e.getMessage());
        }
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }
}

