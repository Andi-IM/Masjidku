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

package org.masjidku.accountant.operasional;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.masjidku.navigation.AppRouter;
import org.masjidku.accounting.client.model.operasional.DonasiOperasional;
import org.masjidku.accounting.client.service.DonasiOperationalService;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditDonaturOperasional {
    private static final Logger log = LoggerFactory.getLogger(EditDonaturOperasional.class);
    private final DonasiOperationalService dao = ServiceProvider.get(DonasiOperationalService.class);

    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtJumlah;
    @FXML
    private DatePicker date;
    private DonasiOperasional donatur;
    private AppRouter mainApp;
    private String operator;

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;

    public void setMainApp(AppRouter mainApp, DonasiOperasional model) {
        String operator = org.masjidku.model.session.dao.SessionManager.getInstance().getCurrentUser().getUsername();
        this.mainApp = mainApp;
        this.donatur = model;
        this.operator = operator;

        if (model.getId() != null) {
            setDonasi(model);
        }
    }

    private void setDonasi(DonasiOperasional model) {
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
                donatur = new DonasiOperasional(nama, jumlah, tanggal, operator);
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
        mainApp.showDonaturOperasional();
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



