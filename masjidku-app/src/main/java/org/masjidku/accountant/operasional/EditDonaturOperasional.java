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
import net.synedra.validatorfx.Validator;
import org.masjidku.accounting.client.model.operasional.DonasiOperasional;
import org.masjidku.accounting.client.service.AccountingClient;
import org.masjidku.navigation.AppRouter;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.masjidku.di.DiProvider.getAppComponent;
import static org.masjidku.util.DaoHelper.saveOrUpdate;
import static org.masjidku.util.ValidationHelper.*;

public class EditDonaturOperasional {
    private static final Logger log = LoggerFactory.getLogger(EditDonaturOperasional.class);
    private final AccountingClient client = ServiceProvider.get(AccountingClient.class);
    private final Validator validator = new Validator();

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

    @FXML
    public void initialize() {
        registerRequiredField(validator, txtNama, "nama", "Nama donatur harus diisi!");
        registerNumericField(validator, txtJumlah, "jumlah", "Jumlah harus diisi!", "Jumlah harus berupa angka!");
        registerDatePicker(validator, date, "tanggal", "Tanggal harus dipilih!");
    }

    public void setMainApp(AppRouter mainApp, DonasiOperasional model) {
        operator = getAppComponent().getSessionManager().getCurrentUser().getUsername();
        this.mainApp = mainApp;
        this.donatur = model;

        if (model.id() != null) {
            setDonasi(model);
        }
    }

    private void setDonasi(DonasiOperasional model) {
        txtNama.setText(model.nama());
        txtJumlah.setText(model.jumlah());
        LocalDate localDate = LocalDate.parse(model.tanggal());
        date.setValue(localDate);
    }

    /**
     * Validating form
     *
     * @return fieldStatus
     */
    private boolean formValidation() {
        return validator.validate();
    }

    @FXML
    public void onSubmitted() {
        if (formValidation()) {
            String nama = txtNama.getText();
            String jumlah = txtJumlah.getText();
            String tanggal = date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if (donatur.id() == null) {
                donatur = new DonasiOperasional(nama, jumlah, tanggal, operator);
            }

            saveOrUpdate(
                    () -> client.isDonasiOperasionalExist(donatur.id()),
                    () -> client.update(new DonasiOperasional(donatur.id(), nama, jumlah, tanggal, operator)),
                    () -> client.save(donatur),
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




