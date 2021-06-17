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
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.masjidku.MainApp;
import org.masjidku.model.kegiatan.Kegiatan;
import org.masjidku.model.kegiatan.KegiatanDao;

import java.sql.SQLException;
import java.time.LocalDate;

public class SecretaryKegiatanForm {

    @FXML
    private TextField txtNamaKegiatan;
    @FXML
    private DatePicker txtTanggal;
    @FXML
    private TextField txtTempat;
    @FXML
    private TextField txtWaktu;
    private Kegiatan kegiatan;
    private MainApp mainApp;
    private String operator;

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;

    public void setMainApp(MainApp mainApp, Kegiatan kegiatan, String operator) {
        this.mainApp = mainApp;
        this.kegiatan = kegiatan;
        this.operator = operator;

        if (kegiatan != null) {
            setKegiatan(kegiatan);
        }
    }

    private void setKegiatan(Kegiatan kegiatan) {
        txtNamaKegiatan.setText(kegiatan.getNama());
        txtTempat.setText(kegiatan.getTempat());
        txtTanggal.setValue(LocalDate.parse(kegiatan.getTanggal()));
        txtWaktu.setText(kegiatan.getWaktu());
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
            String waktu = txtWaktu.getText();
            String tempat = txtTempat.getText();
            String tanggal = txtTanggal.getValue().toString();

            if (kegiatan == null) {
                kegiatan = new Kegiatan(namaKegiatan, waktu, tanggal, tempat, operator);
            }
            KegiatanDao dao = new KegiatanDao();

            if (dao.getConnection()) {
                try {
                    if (dao.isKegiatanExist(kegiatan.getIdKegiatan())) {
                        dao.update(new String[]{
                                kegiatan.getNama(),
                                kegiatan.getWaktu(),
                                kegiatan.getTanggal(),
                                kegiatan.getTempat(),
                                operator,
                                kegiatan.getIdKegiatan()
                        });
                        alertInfo("Success", "Kegiatan telah diperbarui!");
                    } else {
                        dao.save(kegiatan);
                        alertInfo("Success", "Kegiatan telah ditambahkan!");
                    }
                    mainApp.showKegiatan();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                alertError("Error", "Database belum dinyalakan!");
            }
        } else {
            alertError("Error", "Data belum lengkap!");
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

    /**
     * Alert Error Builder
     *
     * @param header  header message
     * @param content content message
     */
    @SuppressWarnings("SameParameterValue")
    private void alertInfo(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(dialogStage);
        alert.setTitle("Prompt");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }

    @SuppressWarnings("SameParameterValue")
    private void alertError(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Prompt");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
