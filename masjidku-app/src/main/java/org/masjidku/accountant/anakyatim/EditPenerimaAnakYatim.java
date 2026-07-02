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

import org.masjidku.accountant.BaseEditController;

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

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.masjidku.di.DiProvider.getAppComponent;
import static org.masjidku.util.AlertHelper.alertError;
import static org.masjidku.util.AlertHelper.alertInfo;
import static org.masjidku.util.Constants.ERROR;
import static org.masjidku.util.Constants.SUCCESS;
import static org.masjidku.util.ValidationHelper.*;

public class EditPenerimaAnakYatim extends BaseEditController<AnakYatim> {
    private static final Logger log = LoggerFactory.getLogger(EditPenerimaAnakYatim.class);
    @FXML
    private Spinner<Integer> spnUsia;
    private AnakYatim anakYatim;
    @FXML
    @Override
    protected void customInitialize() {
        
        
        

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

    

    @Override
    protected boolean isModelExists(AnakYatim model) {
        return model.id() != null;
    }

    @Override
    protected void setModelData(AnakYatim model) {
        txtNama.setText(model.nama());
        txtJumlah.setText(model.jumlah().toPlainString());
        spnUsia.getValueFactory().setValue(model.usia());
        date.setValue(model.tanggal());
    }
    @Override
    @FXML
    public void onSubmitted() {
        if (formValidation()) {
            String nama = txtNama.getText();
            int usia = spnUsia.getValue();
            BigDecimal jumlah = new BigDecimal(txtJumlah.getText());
            LocalDate tanggal = date.getValue();

            if (anakYatim.id() == null) {
                anakYatim = new AnakYatim(nama, usia, jumlah, tanggal, operator);
            }

            try {
                if (client.isAnakYatimExist(anakYatim.id())) {
                    client.update(new AnakYatim(anakYatim.id(), nama, usia, jumlah, tanggal, operator));
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
    @Override
    @FXML
    public void gotoList() {
        mainApp.showDaftarAnakYatim();
    }

    @FXML
    @Override
    protected void customClearForm() {
        
        spnUsia.getValueFactory().setValue(6);
        
        
    }
}



