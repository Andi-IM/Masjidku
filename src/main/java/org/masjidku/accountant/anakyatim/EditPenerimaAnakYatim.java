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
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.masjidku.MainApp;
import org.masjidku.model.accounting.anakyatim.AnakYatim;
import org.masjidku.model.accounting.anakyatim.AnakYatimDao;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditPenerimaAnakYatim implements Initializable {

    @FXML
    private TextField txtNama;
    @FXML
    private Spinner<Integer> spnUsia;
    @FXML
    private TextField txtJumlah;
    @FXML
    private DatePicker date;
    private AnakYatim anakYatim;
    private MainApp mainApp;
    private String operator;
    private LocalDate localDate;

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;


    public void setMainApp(MainApp mainApp, AnakYatim model, String operator) {
        this.mainApp = mainApp;
        this.anakYatim = model;
        this.operator = operator;

        if (model.getId() != null) {
            setModel(model);
        }
    }

    private void setModel(AnakYatim model) {
        txtNama.setText(model.getNama());
        txtJumlah.setText(model.getJumlah());
        spnUsia.getValueFactory().setValue(model.getUsia());
        localDate = LocalDate.parse(model.getTanggal());
        date.setValue(localDate);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtNama.setText("");
        txtJumlah.setText("");
        spnUsia.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 18, 5));
        localDate = LocalDate.parse("2000-01-01");
        date.setValue(localDate);
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
                    return spnUsia.getValueFactory().getValue() > 5 &&
                            spnUsia.getValueFactory().getValue() < 19;
                }
            }
        }
        return false;
    }

    @FXML
    public void onSubmitted() {
        if (formValidation()) {
            String nama = txtNama.getText();
            int usia = spnUsia.getValue();
            String jumlah = txtJumlah.getText();
            String tanggal = date.getValue().toString();

            if (anakYatim == null) {
                anakYatim = new AnakYatim(nama, usia, jumlah, tanggal, operator);
            }

            AnakYatimDao dao = new AnakYatimDao();
            if (dao.getConnection()) {
                try {
                    if (dao.isAnakYatimExist(anakYatim.getId())) {
                        dao.update(new String[]{
                                anakYatim.getId(),
                                anakYatim.getDonatur(),
                                String.valueOf(anakYatim.getUsia()),
                                anakYatim.getJumlah(),
                                anakYatim.getTanggal(),
                                operator
                        });
                        alertInfo("Success", "Data telah diupdate");
                    } else {
                        dao.save(anakYatim);
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
    public void gotoList() {
        mainApp.showDaftarAnakYatim();
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

    @FXML
    public void clearForm() {
        txtNama.clear();
        spnUsia.getValueFactory().setValue(6);
        txtJumlah.clear();
        date.setValue(null);
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
