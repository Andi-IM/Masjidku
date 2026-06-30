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

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.masjidku.navigation.AppRouter;
import org.masjidku.accounting.client.model.pembangunan.DonasiPembangunan;
import org.masjidku.accounting.client.service.DonasiPembangunanService;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditDonaturPembangunan {
    private static final Logger log = LoggerFactory.getLogger(EditDonaturPembangunan.class);
    private final DonasiPembangunanService dao = ServiceProvider.get(DonasiPembangunanService.class);

    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtJumlah;
    @FXML
    private DatePicker date;
    private DonasiPembangunan donatur;
    private AppRouter mainApp;
    private String operator;

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;

    public void setMainApp(AppRouter mainApp, DonasiPembangunan model) {
        String operator = org.masjidku.di.DiProvider.getAppComponent().getSessionManager().getCurrentUser().getUsername();
        this.mainApp = mainApp;
        this.donatur = model;
        this.operator = operator;

        if (model.getId() != null) {
            setDonatur(model);
        }
    }

    private void setDonatur(DonasiPembangunan model) {
        txtNama.setText(model.getDonatur());
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
        return !txtNama.getText().isBlank() && !txtJumlah.getText().isBlank() && txtJumlah.getText().matches("\\d+") && date.getValue() != null;
    }

    @FXML
    public void onSubmitted() {
        if (formValidation()) {
            String nama = txtNama.getText();
            String jumlah = txtJumlah.getText();
            String tanggal = date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if (donatur.getId() == null) {
                donatur = new DonasiPembangunan(nama, jumlah, tanggal, operator);
            }

            org.masjidku.util.DaoHelper.saveOrUpdate(
                () -> dao.isDonaturExist(donatur.getId()),
                () -> dao.update(new String[]{
                        donatur.getId(),
                        donatur.getDonatur(),
                        donatur.getJumlah(),
                        donatur.getTanggal(),
                        operator
                }),
                () -> dao.save(donatur),
                dialogStage, log
            );
        } else {
            org.masjidku.util.AlertHelper.alertError(dialogStage, "Error", "Data belum lengkap!");
        }
    }

    @FXML
    public void gotoList() {
        mainApp.showDonaturPembangunan();
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @FXML
    public void clearForm() {
        txtNama.clear();
        txtJumlah.clear();
        date.getEditor().clear();
    }


}



