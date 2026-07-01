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

package org.masjidku.accountant.tpa;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.synedra.validatorfx.Validator;
import org.masjidku.accounting.client.model.tpa.TpaMasuk;
import org.masjidku.accounting.client.service.AccountingClient;
import org.masjidku.navigation.AppRouter;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.masjidku.di.DiProvider.getAppComponent;
import static org.masjidku.util.AlertHelper.alertError;
import static org.masjidku.util.Constants.ERROR;
import static org.masjidku.util.DaoHelper.saveOrUpdate;
import static org.masjidku.util.ValidationHelper.*;

public class EditDonaturTpa {
    private static final Logger log = LoggerFactory.getLogger(EditDonaturTpa.class);
    private final AccountingClient client = ServiceProvider.get(AccountingClient.class);
    private final Validator validator = new Validator();

    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtJumlah;
    @FXML
    private DatePicker date;

    private TpaMasuk donatur;
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

    public void setMainApp(AppRouter mainApp, TpaMasuk model) {
        operator = getAppComponent().getSessionManager().getCurrentUsername();
        this.mainApp = mainApp;
        this.donatur = model;

        if (model.id() != null) {
            setDonatur(model);
        }
    }

    public void setDonatur(TpaMasuk model) {
        txtNama.setText(model.donatur());
        txtJumlah.setText(model.jumlah().toPlainString());
        date.setValue(model.tanggal());
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
            BigDecimal jumlah = new BigDecimal(txtJumlah.getText());
            LocalDate tanggal = date.getValue();

            if (donatur == null) {
                donatur = new TpaMasuk(nama, jumlah, tanggal, operator);
            }

            saveOrUpdate(
                    () -> client.isTpaMasukExist(donatur.id()),
                    () -> client.update(new TpaMasuk(donatur.id(), nama, jumlah, tanggal, operator)),
                    () -> client.save(donatur),
                    dialogStage, log
            );
        } else {
            alertError(dialogStage, ERROR, "Data belum lengkap!");
        }
    }

    @FXML
    public void gotoList() {
        mainApp.showDonaturTpa();
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



