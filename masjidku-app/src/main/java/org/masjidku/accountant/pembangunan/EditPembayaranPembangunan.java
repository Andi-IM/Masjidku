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

package org.masjidku.accountant.pembangunan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.masjidku.util.ServiceProvider;
import org.masjidku.accounting.client.service.*;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.masjidku.MainApp;
import org.masjidku.accounting.client.model.pembangunan.Pembangunan;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditPembayaranPembangunan {
    private static final Logger log = LoggerFactory.getLogger(EditPembayaranPembangunan.class);
    private final PembangunanService dao = ServiceProvider.get(PembangunanService.class);

    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtKeterangan;
    @FXML
    private TextField txtJumlah;
    @FXML
    private DatePicker date;
    private Pembangunan model;
    private MainApp mainApp;
    private String operator;

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;

    public void setMainApp(MainApp mainApp, Pembangunan model) {
        String operator = org.masjidku.model.session.SessionManager.getInstance().getCurrentUser().getUsername();
        this.mainApp = mainApp;
        this.model = model;
        this.operator = operator;

        if (model.getId() != null) {
            setModel(model);
        }
    }

    private void setModel(Pembangunan model) {
        txtNama.setText(model.getNama());
        txtKeterangan.setText(model.getKeterangan());
        txtJumlah.setText(model.getJumlah());
        LocalDate localDate = LocalDate.parse(model.getTanggal());
        date.setValue(localDate);
    }

    /**
     * Validating form
     *
     * @return fieldStatus
     */
    private boolean formValidation() {
        if (!txtNama.getText().isBlank()) {
            if (!txtKeterangan.getText().isBlank()) {
                if (!txtJumlah.getText().isBlank()) {
                    if (txtJumlah.getText().matches("[0-9]")) {
                        return date.getEditor().getText().isBlank();
                    }
                }
            }
        }
        return false;
    }

    @FXML
    public void onSubmitted() {
        if (formValidation()) {
            String nama = txtNama.getText();
            String keterangan = txtKeterangan.getText();
            String jumlah = txtJumlah.getText();
            String tanggal = date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if (model.getId() == null) {
                model = new Pembangunan(nama, keterangan, jumlah, tanggal, operator);
            }

            try {
                if (dao.isDataExist(model.getId())) {
                    dao.update(new String[]{
                            model.getId(),
                            model.getNama(),
                            model.getKeterangan(),
                            model.getJumlah(),
                            model.getTanggal(),
                            operator
                    });
                    org.masjidku.util.AlertHelper.alertInfo(dialogStage, "Success", "Data telah diupdate");
                } else {
                    dao.save(model);
                }
            } catch (SQLException e) {
                log.error("An error occurred", e);
            }
        } else {
            org.masjidku.util.AlertHelper.alertError(dialogStage, "Error", "Data belum lengkap!");
        }
    }

    @FXML
    public void gotoList() {
        mainApp.showAlokasiPembangunan();
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @FXML
    public void clearForm() {
        txtNama.clear();
        txtKeterangan.clear();
        txtJumlah.clear();
        date.getEditor().clear();
    }


}
