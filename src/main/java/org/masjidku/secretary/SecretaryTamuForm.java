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

package org.masjidku.secretary;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.masjidku.MainApp;
import org.masjidku.model.kegiatan.Tamu;
import org.masjidku.model.kegiatan.TamuDao;

import java.sql.SQLException;

public class SecretaryTamuForm {

    @FXML
    public TextField txtNomorTelp;
    @FXML
    public TextField txtNamaTamu;
    @FXML
    public TextArea txtAlamat;

    private Tamu tamu;
    private MainApp mainApp;

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;
    private String operator;

    public void setMainApp(MainApp mainApp, Tamu tamu, String operator) {
        this.mainApp = mainApp;
        this.tamu = tamu;
        this.operator = operator;

        if (tamu != null){
            setTamu(tamu);
        }
    }

    private void setTamu(Tamu tamu) {
        txtNamaTamu.setText(tamu.getNama());
        txtAlamat.setText(tamu.getAlamat());
        txtNomorTelp.setText(tamu.getNotelp());
    }

    @FXML
    public void onBackAction() { mainApp.showTamu(); }

    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }

    @FXML
    public void clearForm() {
        txtNamaTamu.clear();
        txtAlamat.clear();
        txtNomorTelp.clear();
    }

    @FXML
    public void onUserSubmitted() {
        if (formValidation()){
            String namaTamu = txtNamaTamu.getText();
            String alamat = txtAlamat.getText();
            String noTelp = txtNomorTelp.getText();

            if (tamu == null){
                tamu = new Tamu(namaTamu, alamat, noTelp, operator);
            }
            TamuDao dao = new TamuDao();

            if (dao.getConnection()){
                try {
                    if (dao.isTamuExist(tamu.getIdTamu())){
                        dao.update(new String[]{
                                tamu.getNama(),
                                tamu.getAlamat(),
                                tamu.getNotelp(),
                                operator,
                                tamu.getIdTamu()
                        });
                        alertInfo("Success","Tamu telah diupdate");
                    } else {
                        dao.save(tamu);
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

    private boolean formValidation() {
        if (!txtNamaTamu.getText().isBlank()){
            if (!txtAlamat.getText().isBlank()){
                return !txtNomorTelp.getText().isBlank();
            }
        }
        return false;
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
