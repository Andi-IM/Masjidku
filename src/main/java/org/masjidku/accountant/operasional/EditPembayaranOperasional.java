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
import org.masjidku.model.accounting.operasional.Operasional;
import org.masjidku.model.accounting.operasional.OperationalDao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditPembayaranOperasional {

    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtKeterangan;
    @FXML
    private TextField txtJumlah;
    @FXML
    private DatePicker date;
    private Operasional model;
    private MainApp mainApp;
    private String operator;

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;
    public void setMainApp(MainApp mainApp, Operasional model, String operator) {
        this.mainApp = mainApp;
        this.model = model;
        this.operator = operator;

        if (model.getId() != null) {
            setModel(model);
        }
    }

    private void setModel(Operasional model) {
        txtNama.setText(model.getNama());
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
        if (!txtNama.getText().isBlank()) {
            if (!txtKeterangan.getText().isBlank()) {
                if (!txtJumlah.getText().isBlank()) {
                    if (txtJumlah.getText().matches("[0-9]")) {
                        return date.getEditor().getText().isBlank();
                    }
                }
            }
        }
        return false;
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

            OperationalDao dao = new OperationalDao();
            if (dao.getConnection()) {
                try {
                    if (dao.isDataExist(model.getId())) {
                        dao.update(new String[]{
                                model.getId(),
                                nama,
                                keterangan,
                                jumlah,
                                tanggal,
                                operator
                        });
                        alertInfo("Success", "Data telah diupdate");
                        mainApp.showOperasional();
                    } else {
                        dao.save(model);
                        alertInfo("Success", "Data telah ditambahkan");
                        mainApp.showOperasional();
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
    public void gotoList() { mainApp.showAlokasiOperasional(); }

    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }

    @FXML
    public void clearForm() {
        txtNama.clear();
        txtKeterangan.clear();
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
