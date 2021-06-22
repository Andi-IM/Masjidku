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
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.masjidku.MainApp;
import org.masjidku.model.accounting.zakat.ZakatKeluar;
import org.masjidku.model.accounting.zakat.ZakatKeluarDao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditPenerimaZakat {

    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtJumlah;
    @FXML
    private DatePicker date;
    private ZakatKeluar penerima;
    private MainApp mainApp;
    private String operator;

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;

    public void setMainApp(MainApp mainApp, ZakatKeluar model, String operator) {
        this.mainApp = mainApp;
        this.penerima = model;
        this.operator = operator;

        if (model.getId() != null){
            setModel(model);
        }
    }

    private void setModel(ZakatKeluar model){
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
        if (!txtNama.getText().isBlank()) {
            if (!txtJumlah.getText().isBlank()) {
                if (txtJumlah.getText().matches("[0-9]")) {
                    return date.getEditor().getText().isBlank();
                }
            }
        }
        return false;
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

            ZakatKeluarDao dao = new ZakatKeluarDao();
            if (dao.getConnection()) {
                try {
                    if (dao.isDataExist(penerima.getId())) {
                        dao.update(new String[]{
                                penerima.getId(),
                                penerima.getNama(),
                                penerima.getJumlah(),
                                penerima.getTanggal(),
                                operator
                        });
                        alertInfo("Success", "Data telah diupdate");
                    } else {
                        dao.save(penerima);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                alertError("Error", "Database belum ditanyakan!");
            }
        } else {
            alertError("Error", "Data belum lengkap!");
        }
    }



    @FXML
    public void gotoList() { mainApp.showDaftarPenerimaZakat(); }

    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }

    /**
     * Alert Error Builder
     *
     * @param header  header message
     * @param content content message
     */
    @SuppressWarnings("SameParameterValue")
    private void alertInfo(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(dialogStage);
        alert.setTitle("Prompt");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }

    @SuppressWarnings("SameParameterValue")
    private void alertError(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Prompt");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
