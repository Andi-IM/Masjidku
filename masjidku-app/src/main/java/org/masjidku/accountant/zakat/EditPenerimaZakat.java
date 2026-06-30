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
import net.synedra.validatorfx.Validator;
import org.masjidku.accounting.client.model.zakat.ZakatKeluar;
import org.masjidku.accounting.client.service.AccountingClient;
import org.masjidku.navigation.AppRouter;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.masjidku.di.DiProvider.getAppComponent;
import static org.masjidku.util.AlertHelper.alertError;
import static org.masjidku.util.AlertHelper.alertInfo;
import static org.masjidku.util.Constants.ERROR;
import static org.masjidku.util.Constants.SUCCESS;
import static org.masjidku.util.ValidationHelper.*;

public class EditPenerimaZakat {
    private static final Logger log = LoggerFactory.getLogger(EditPenerimaZakat.class);
    private final AccountingClient client = ServiceProvider.get(AccountingClient.class);
    private final Validator validator = new Validator();

    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtJumlah;
    @FXML
    private DatePicker date;
    private ZakatKeluar penerima;
    private AppRouter mainApp;
    private String operator;

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;

    @FXML
    public void initialize() {
        registerRequiredField(validator, txtNama, "nama", "Nama penerima harus diisi!");
        registerNumericField(validator, txtJumlah, "jumlah", "Jumlah harus diisi!", "Jumlah harus berupa angka!");
        registerDatePicker(validator, date, "tanggal", "Tanggal harus dipilih!");
    }

    public void setMainApp(AppRouter mainApp, ZakatKeluar model) {
        operator = getAppComponent().getSessionManager().getCurrentUser().getUsername();
        this.mainApp = mainApp;
        this.penerima = model;

        if (model.getId() != null) {
            setModel(model);
        }
    }

    private void setModel(ZakatKeluar model) {
        txtNama.setText(model.getNama());
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

    /**
     * Validating form
     *
     * @return fieldStatus
     */
    private boolean formValidation() {
        return validator.validate();
    }

    @FXML
    public void onUserSubmitted() {
        if (formValidation()) {
            String nama = txtNama.getText();
            String jumlah = txtJumlah.getText();
            String tanggal = date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if (penerima == null) {
                penerima = new ZakatKeluar(nama, jumlah, tanggal, operator);
            }

            try {
                if (client.isZakatKeluarExist(penerima.getId())) {
                    client.update(new ZakatKeluar(penerima.getId(), penerima.getNama(), penerima.getJumlah(), penerima.getTanggal(), operator));
                    alertInfo(dialogStage, SUCCESS, "Data telah diupdate");
                } else {
                    client.save(penerima);
                }
            } catch (Exception e) {
                log.error("An error occurred", e);
            }
        } else {
            alertError(dialogStage, ERROR, "Data belum lengkap!");
        }
    }


    @FXML
    public void gotoList() {
        mainApp.showDaftarPenerimaZakat();
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }


}



