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

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.synedra.validatorfx.Validator;
import org.masjidku.accounting.client.model.anakyatim.AnakYatim;
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

public class EditPenerimaAnakYatim {
    private static final Logger log = LoggerFactory.getLogger(EditPenerimaAnakYatim.class);
    private final AccountingClient client = ServiceProvider.get(AccountingClient.class);
    private final Validator validator = new Validator();

    @FXML
    private TextField txtNama;
    @FXML
    private Spinner<Integer> spnUsia;
    @FXML
    private TextField txtJumlah;
    @FXML
    private DatePicker date;
    private AnakYatim anakYatim;
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

        validator.createCheck()
                .dependsOn("usia", spnUsia.valueProperty())
                .withMethod(c -> {
                    Integer val = c.get("usia");
                    if (val == null || val <= 5 || val >= 19) {
                        c.error("Usia anak yatim harus di antara 6 dan 18 tahun!");
                    }
                })
                .decorates(spnUsia);
    }

    public void setMainApp(AppRouter mainApp, AnakYatim model) {
        operator = getAppComponent().getSessionManager().getCurrentUser().getUsername();
        this.mainApp = mainApp;
        this.anakYatim = model;

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
        return validator.validate();
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
                if (client.isAnakYatimExist(anakYatim.getId())) {
                    client.update(new AnakYatim(anakYatim.getId(), nama, usia, jumlah, tanggal, operator));
                    alertInfo(dialogStage, ERROR, "Data telah diupdate");
                } else {
                    client.save(anakYatim);
                    alertInfo(dialogStage, SUCCESS, "Data telah ditambahkan");
                    mainApp.showAnakYatim();
                }
            } catch (Exception e) {
                log.error("An error occurred", e);
            }
        } else {
            alertError(dialogStage, "Error", "Data belum lengkap!");
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



