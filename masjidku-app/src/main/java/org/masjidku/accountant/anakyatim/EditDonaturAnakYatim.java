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
import org.masjidku.accountant.BaseEditController;
import org.masjidku.accounting.client.model.anakyatim.DonasiAYatim;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.masjidku.util.DaoHelper.saveOrUpdate;

public class EditDonaturAnakYatim extends BaseEditController<DonasiAYatim> {
    private static final Logger log = LoggerFactory.getLogger(EditDonaturAnakYatim.class);


    @Override
    protected boolean isModelExists(DonasiAYatim model) {
        return model.id() != null;
    }

    @Override
    protected void setModelData(DonasiAYatim model) {
        txtNama.setText(model.donatur());
        txtJumlah.setText(model.jumlah().toPlainString());
        date.setValue(model.tanggal());
    }

    @Override
    protected void processSubmission() {

        if (model == null) {
            model = new DonasiAYatim(txtNama.getText(), new BigDecimal(txtJumlah.getText()), date.getValue(), operator);
        }

        saveOrUpdate(
                () -> client.isDonasiAYatimExist(model.id()),
                () -> client.update(new DonasiAYatim(model.id(), txtNama.getText(), new BigDecimal(txtJumlah.getText()), date.getValue(), operator)),
                () -> client.save(model),
                dialogStage, log
        );
    }

    @Override
    @FXML
    public void gotoList() {
        mainApp.showDonasiAYatim();
    }

    @Override
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }
}




