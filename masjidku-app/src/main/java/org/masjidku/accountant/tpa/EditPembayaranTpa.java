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

import org.masjidku.accountant.BaseEditController;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.synedra.validatorfx.Validator;
import org.masjidku.accounting.client.model.tpa.TpaKeluar;
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

public class EditPembayaranTpa extends BaseEditController<TpaKeluar> {
    private static final Logger log = LoggerFactory.getLogger(EditPembayaranTpa.class);

    

    @Override
    protected boolean isModelExists(TpaKeluar model) {
        return model.id() != null;
    }

    @Override
    protected void setModelData(TpaKeluar model) {
        txtNama.setText(model.nama());
        txtKeterangan.setText(model.keterangan());
        txtJumlah.setText(model.jumlah().toPlainString());
        date.setValue(model.tanggal());
    }
    @Override
    protected void processSubmission() {
            String nama = txtNama.getText();
            String keterangan = txtKeterangan.getText();
            BigDecimal jumlah = new BigDecimal(txtJumlah.getText());
            LocalDate tanggal = date.getValue();

            if (model.id() == null) {
                model = new TpaKeluar(nama, keterangan, jumlah, tanggal, operator);
            }

            saveOrUpdate(
                    () -> client.isTpaKeluarExist(model.id()),
                    () -> client.update(new TpaKeluar(model.id(), nama, keterangan, jumlah, tanggal, operator)),
                    () -> client.save(model),
                    dialogStage, log
            );
    }
    @Override
    @FXML
    public void gotoList() {
        mainApp.showAlokasiTpa();
    }


}



