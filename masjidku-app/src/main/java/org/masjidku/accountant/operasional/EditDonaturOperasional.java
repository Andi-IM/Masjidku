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

import org.masjidku.accountant.BaseEditController;

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

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.masjidku.di.DiProvider.getAppComponent;
import static org.masjidku.util.DaoHelper.saveOrUpdate;
import static org.masjidku.util.ValidationHelper.*;

public class EditDonaturOperasional extends BaseEditController<DonasiOperasional> {
    private static final Logger log = LoggerFactory.getLogger(EditDonaturOperasional.class);
    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtJumlah;
    @FXML
    private DatePicker date;
    @FXML
    public void initialize() {
        registerRequiredField(validator, txtNama, "nama", "Nama model harus diisi!");
        registerNumericField(validator, txtJumlah, "jumlah", "Jumlah harus diisi!", "Jumlah harus berupa angka!");
        registerDatePicker(validator, date, "tanggal", "Tanggal harus dipilih!");
    }

    

    @Override
    protected boolean isModelExists(DonasiOperasional model) {
        return model.id() != null;
    }

    @Override
    protected void setModelData(DonasiOperasional model) {
        txtNama.setText(model.nama());
        txtJumlah.setText(model.jumlah().toPlainString());
        date.setValue(model.tanggal());
    }

    @FXML
    public void onSubmitted() {
        if (formValidation()) {
            String nama = txtNama.getText();
            BigDecimal jumlah = new BigDecimal(txtJumlah.getText());
            LocalDate tanggal = date.getValue();

            if (model.id() == null) {
                model = new DonasiOperasional(nama, jumlah, tanggal, operator);
            }

            saveOrUpdate(
                    () -> client.isDonasiOperasionalExist(model.id()),
                    () -> client.update(new DonasiOperasional(model.id(), nama, jumlah, tanggal, operator)),
                    () -> client.save(model),
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
    public void clearForm() {
        txtNama.clear();
        txtJumlah.clear();
        date.getEditor().clear();
    }


}




