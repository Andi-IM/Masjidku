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
import net.synedra.validatorfx.Validator;
import org.masjidku.accounting.client.model.zakat.ZakatKeluar;
import org.masjidku.accounting.client.service.AccountingClient;
import org.masjidku.navigation.AppRouter;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.masjidku.di.DiProvider.getAppComponent;
import static org.masjidku.util.AlertHelper.alertError;
import static org.masjidku.util.AlertHelper.alertInfo;
import static org.masjidku.util.Constants.ERROR;
import static org.masjidku.util.Constants.SUCCESS;
import static org.masjidku.util.ValidationHelper.*;

public class EditPenerimaZakat extends BaseEditController<ZakatKeluar> {
    private static final Logger log = LoggerFactory.getLogger(EditPenerimaZakat.class);

    

    @Override
    protected boolean isModelExists(ZakatKeluar model) {
        return model.id() != null;
    }

    @Override
    protected void setModelData(ZakatKeluar model) {
        txtNama.setText(model.nama());
        txtJumlah.setText(model.jumlah().toPlainString());
        date.setValue(model.tanggal());
    }
    @Override
    protected void processSubmission() {

            if (model == null) {
                model = new ZakatKeluar(txtNama.getText(), new BigDecimal(txtJumlah.getText()), date.getValue(), operator);
            }

            try {
                if (client.isZakatKeluarExist(model.id())) {
                    client.update(new ZakatKeluar(model.id(), model.nama(), model.jumlah(), model.tanggal(), operator));
                    alertInfo(dialogStage, SUCCESS, "Data telah diupdate");
                } else {
                    client.save(model);
                }
            } catch (Exception e) {
                log.error("An error occurred", e);
            }
    }
    @Override
    @FXML
    public void gotoList() {
        mainApp.showDaftarPenerimaZakat();
    }

    }



