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

package org.masjidku.accountant.zakat;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.masjidku.navigation.AppRouter;
import org.masjidku.accounting.client.model.zakat.ZakatMasuk;


import org.masjidku.accounting.client.service.AccountingClient;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import net.synedra.validatorfx.Validator;

import static org.masjidku.di.DiProvider.getAppComponent;
import static org.masjidku.util.AlertHelper.alertError;
import static org.masjidku.util.Constants.ERROR;
import static org.masjidku.util.ValidationHelper.*;

public class EditDonaturZakat {
    private static final Logger log = LoggerFactory.getLogger(EditDonaturZakat.class);
    private final AccountingClient client = ServiceProvider.get(AccountingClient.class);
    private final Validator validator = new Validator();

    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtJumlah;
    @FXML
    private DatePicker date;
    private ZakatMasuk donatur;
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

    public void setMainApp(AppRouter mainApp, ZakatMasuk model) {
        operator = getAppComponent().getSessionManager().getCurrentUser().getUsername();
        this.mainApp = mainApp;
        this.donatur = model;

        if (model.getId() != null) {
            setDonatur(model);
        }
    }

    public void setDonatur(ZakatMasuk model) {
        txtNama.setText(model.getDonatur());
        txtJumlah.setText(model.getJumlah());
        LocalDate localDate = LocalDate.parse(model.getTanggal());
        date.setValue(localDate);
    }

    @FXML
    public void clearForm() {
        txtNama.clear();
        txtJumlah.clear();
        date.getEditor().clear();
    }

    @FXML
    public void onUserSubmitted() {
        if (formValidation()) {
            String nama = txtNama.getText();
            String jumlah = txtJumlah.getText();
            String tanggal = date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if (donatur == null) {
                donatur = new ZakatMasuk(nama, jumlah, tanggal, operator);
            }

            org.masjidku.util.DaoHelper.saveOrUpdate(
                () -> client.isZakatMasukExist(donatur.getId()),
                () -> client.update(new ZakatMasuk(donatur.getId(), nama, jumlah, tanggal, operator)),
                () -> client.save(donatur),
                dialogStage, log
            );
        } else {
            alertError(dialogStage, ERROR, "Data belum lengkap!");
        }
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
    public void gotoList() {
        mainApp.showDonaturZakat();
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }


}




