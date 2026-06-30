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

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.masjidku.navigation.AppRouter;
import org.masjidku.accounting.client.model.operasional.Operasional;
import org.masjidku.accounting.client.service.OperationalService;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditPembayaranOperasional {
    private static final Logger log = LoggerFactory.getLogger(EditPembayaranOperasional.class);
    private final OperationalService dao = ServiceProvider.get(OperationalService.class);

    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtKeterangan;
    @FXML
    private TextField txtJumlah;
    @FXML
    private DatePicker date;
    private Operasional model;
    private AppRouter mainApp;
    private String operator;

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;

    public void setMainApp(AppRouter mainApp, Operasional model) {
        String operator = org.masjidku.model.session.dao.SessionManager.getInstance().getCurrentUser().getUsername();
        this.mainApp = mainApp;
        this.model = model;
        this.operator = operator;

        if (model.getId() != null) {
            setModel(model);
        }
    }

    private void setModel(Operasional model) {
        txtNama.setText(model.getTujuan());
        txtKeterangan.setText(model.getKeterangan());
        txtJumlah.setText(model.getJumlah());
        LocalDate localDate = LocalDate.parse(model.getTanggal());
        date.setValue(localDate);
    }

    /**
     * Validating form
     *
     * @return fieldStatus
     */
    private boolean formValidation() {
        return !txtNama.getText().isBlank() && !txtKeterangan.getText().isBlank() && !txtJumlah.getText().isBlank() && txtJumlah.getText().matches("\\d+") && date.getValue() != null;
    }

    @FXML
    public void onSubmitted() {
        if (formValidation()) {
            String nama = txtNama.getText();
            String keterangan = txtKeterangan.getText();
            String jumlah = txtJumlah.getText();
            String tanggal = date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if (model.getId() == null) {
                model = new Operasional(nama, keterangan, jumlah, tanggal, operator);
            }

            org.masjidku.util.DaoHelper.saveOrUpdate(
                () -> dao.isDataExist(model.getId()),
                () -> dao.update(new String[]{
                        model.getId(),
                        model.getTujuan(),
                        model.getKeterangan(),
                        model.getJumlah(),
                        model.getTanggal(),
                        operator
                }),
                () -> dao.save(model),
                dialogStage, log
            );
        } else {
            org.masjidku.util.AlertHelper.alertError(dialogStage, "Error", "Data belum lengkap!");
        }
    }

    @FXML
    public void gotoList() {
        mainApp.showAlokasiOperasional();
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @FXML
    public void clearForm() {
        txtNama.clear();
        txtKeterangan.clear();
        txtJumlah.clear();
        date.getEditor().clear();
    }


}


