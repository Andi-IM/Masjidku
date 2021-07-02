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
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.masjidku.MainApp;
import org.masjidku.model.accounting.operasional.DonasiOperasional;
import org.masjidku.model.accounting.operasional.DonasiOperationalDao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditDonaturOperasional {

    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtJumlah;
    @FXML
    private DatePicker date;
    private DonasiOperasional donatur;
    private MainApp mainApp;
    private String operator;

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;

    public void setMainApp(MainApp mainApp, DonasiOperasional model, String operator) {
        this.mainApp = mainApp;
        this.donatur = model;
        this.operator = operator;

        if (model.getId() != null) {
            setDonasi(model);
        }
    }

    private void setDonasi(DonasiOperasional model) {
        txtNama.setText(model.getDonatur());
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
        if (!txtNama.getText().isBlank()) {
            if (!txtJumlah.getText().isBlank()){
                if (txtJumlah.getText().matches("[0-9]")){
                    return date.getEditor().getText().isBlank();
                }
            }
        }
        return false;
    }

    @FXML
    public void onSubmitted() {
        if (formValidation()) {
            String nama = txtNama.getText();
            String jumlah = txtJumlah.getText();
            String tanggal = date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if (donatur.getId() == null) {
                donatur = new DonasiOperasional(nama, jumlah, tanggal, operator);
            }

            DonasiOperationalDao dao = new DonasiOperationalDao();
            if (dao.getConnection()) {
                try {
                    if (dao.isDonaturExist(donatur.getId())) {
                        dao.update(new String[]{
                                donatur.getId(),
                                nama,
                                jumlah,
                                tanggal,
                                operator
                        });
                        alertInfo("Success", "Data telah diupdate");
                        mainApp.showDonaturOperasional();
                    } else {
                        dao.save(donatur);
                        alertInfo("Success", "Data telah ditambahkan");
                        mainApp.showDonaturOperasional();
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
    public void gotoList() { mainApp.showDonaturOperasional(); }

    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }

    @FXML
    public void clearForm() {
        txtNama.clear();
        txtJumlah.clear();
        date.getEditor().clear();
    }

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
