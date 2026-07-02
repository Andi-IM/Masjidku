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

import org.masjidku.accountant.BaseEditController;

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

import java.math.BigDecimal;
import java.time.LocalDate;
import net.synedra.validatorfx.Validator;

import static org.masjidku.di.DiProvider.getAppComponent;
import static org.masjidku.util.AlertHelper.alertError;
import static org.masjidku.util.Constants.ERROR;
import static org.masjidku.util.ValidationHelper.*;

public class EditDonaturZakat extends BaseEditController<ZakatMasuk> {
    private static final Logger log = LoggerFactory.getLogger(EditDonaturZakat.class);

    

    @Override
    protected boolean isModelExists(ZakatMasuk model) {
        return model.id() != null;
    }

    @Override
    protected void setModelData(ZakatMasuk model) {
        txtNama.setText(model.donatur());
        txtJumlah.setText(model.jumlah().toPlainString());
        date.setValue(model.tanggal());
    }
    @Override
    @FXML
    public void onSubmitted() {
        if (formValidation()) {
            String nama = txtNama.getText();
            BigDecimal jumlah = new BigDecimal(txtJumlah.getText());
            LocalDate tanggal = date.getValue();

            if (model == null) {
                model = new ZakatMasuk(nama, jumlah, tanggal, operator);
            }

            org.masjidku.util.DaoHelper.saveOrUpdate(
                () -> client.isZakatMasukExist(model.id()),
                () -> client.update(new ZakatMasuk(model.id(), nama, jumlah, tanggal, operator)),
                () -> client.save(model),
                dialogStage, log
            );
        } else {
            alertError(dialogStage, ERROR, "Data belum lengkap!");
        }
    }
    @Override
    @FXML
    public void gotoList() {
        mainApp.showDonaturZakat();
    }

    }




