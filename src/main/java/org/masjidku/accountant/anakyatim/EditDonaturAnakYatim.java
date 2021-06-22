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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.masjidku.MainApp;
import org.masjidku.model.accounting.anakyatim.DonasiAYatim;
import org.masjidku.model.accounting.anakyatim.DonasiAYatimDao;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditDonaturAnakYatim implements Initializable {
    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtJumlah;
    @FXML
    private DatePicker date;
    private DonasiAYatim donatur;
    private MainApp mainApp;
    private String operator;
    private LocalDate localDate;

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;

    public void setMainApp(MainApp mainApp, DonasiAYatim model, String operator) {
        this.mainApp = mainApp;
        this.donatur = model;
        this.operator = operator;

        if (model.getId() != null && model.getTanggal() != null) {
            setDonasi(model);
        }
    }

    private void setDonasi(DonasiAYatim model) {
        txtNama.setText(model.getDonatur());
        txtJumlah.setText(model.getJumlah());
        localDate = LocalDate.parse(model.getTanggal());
        date.setValue(localDate);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtNama.setText("");
        txtJumlah.setText("");
        localDate = LocalDate.parse("2000-01-01");
        date.setValue(localDate);
    }

    @FXML
    public void clearForm() {
        txtNama.clear();
        txtJumlah.clear();
        localDate = LocalDate.parse("2000-01-01");
        date.setValue(localDate);
    }

    @FXML
    public void onSubmitted() {
        if (formValidation()) {
            String nama = txtNama.getText();
            String jumlah = txtJumlah.getText();
            String tanggal = date.getValue().toString();

            if (donatur == null) {
                donatur = new DonasiAYatim(nama, jumlah, tanggal, operator);
            }

            DonasiAYatimDao dao = new DonasiAYatimDao();
            if (dao.getConnection()) {
                try {
                    if (dao.isDonaturExist(donatur.getId())) {
                        dao.update(new String[]{
                                donatur.getId(),
                                donatur.getDonatur(),
                                donatur.getJumlah(),
                                donatur.getTanggal(),
                                operator
                        });
                        alertInfo("Success", "Data telah diupdate");
                    } else {
                        dao.save(donatur);
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

    /**
     * Validating form
     *
     * @return fieldStatus
     */
    private boolean formValidation() {
        if (!txtNama.getText().isBlank()) {
            if (!txtJumlah.getText().isBlank()){
                // Parses the first date
                LocalDate dt1 = LocalDate.parse("2000-01-01");
                return date.getValue().isAfter(dt1);
            }
        }
        return false;
    }

    @FXML
    public void gotoList() {
        mainApp.showDonasiAYatim();
    }

    public void onLogoutClick() {
        mainApp.onLogoutAction();
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
