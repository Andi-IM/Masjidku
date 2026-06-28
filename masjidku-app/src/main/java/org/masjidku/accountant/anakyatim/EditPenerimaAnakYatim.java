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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.masjidku.util.ServiceProvider;
import org.masjidku.accounting.client.service.*;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.masjidku.MainApp;
import org.masjidku.accounting.client.model.anakyatim.AnakYatim;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditPenerimaAnakYatim {
    private static final Logger log = LoggerFactory.getLogger(EditPenerimaAnakYatim.class);
    private final AnakYatimService dao = ServiceProvider.get(AnakYatimService.class);

    @FXML
    private TextField txtNama;
    @FXML
    private Spinner<Integer> spnUsia;
    @FXML
    private TextField txtJumlah;
    @FXML
    private DatePicker date;
    private AnakYatim anakYatim;
    private MainApp mainApp;
    private String operator;

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;


    public void setMainApp(MainApp mainApp, AnakYatim model) {
        String operator = org.masjidku.model.session.SessionManager.getInstance().getCurrentUser().getUsername();
        this.mainApp = mainApp;
        this.anakYatim = model;
        this.operator = operator;

        if (model.getId() != null) {
            setModel(model);
        }
    }

    private void setModel(AnakYatim model) {
        txtNama.setText(model.getNama());
        txtJumlah.setText(model.getJumlah());
        spnUsia.getValueFactory().setValue(model.getUsia());
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
            if (!txtJumlah.getText().isBlank()) {
                if (txtJumlah.getText().matches("[0-9]")) {
                    if (date.getEditor().getText().isBlank()){
                        return spnUsia.getValueFactory().getValue() > 5 &&
                                spnUsia.getValueFactory().getValue() < 19;
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
            int usia = spnUsia.getValue();
            String jumlah = txtJumlah.getText();
            String tanggal = date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if (anakYatim.getId() == null) {
                anakYatim = new AnakYatim(nama, usia, jumlah, tanggal, operator);
            }

            try {
                if (dao.isAnakYatimExist(anakYatim.getId())) {
                    dao.update(new String[]{
                            anakYatim.getId(),
                            anakYatim.getNama(),
                            String.valueOf(anakYatim.getUsia()),
                            anakYatim.getJumlah(),
                            anakYatim.getTanggal(),
                            operator
                    });
                    org.masjidku.util.AlertHelper.alertInfo(dialogStage, "Success", "Data telah diupdate");
                } else {
                    dao.save(anakYatim);
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
        mainApp.showDaftarAnakYatim();
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @FXML
    public void clearForm() {
        txtNama.clear();
        spnUsia.getValueFactory().setValue(6);
        txtJumlah.clear();
        date.getEditor().clear();
    }


}
