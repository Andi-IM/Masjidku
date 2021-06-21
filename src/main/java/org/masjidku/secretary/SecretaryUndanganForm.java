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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.masjidku.MainApp;
import org.masjidku.model.kegiatan.KegiatanDao;
import org.masjidku.model.kegiatan.TamuDao;
import org.masjidku.model.kegiatan.TamuKegiatan;
import org.masjidku.model.kegiatan.TamuKegiatanDao;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SecretaryUndanganForm implements Initializable {

    @FXML
    public ChoiceBox<String>cbKegiatan;
    @FXML
    public ChoiceBox<String>cbTamu;
    @FXML
    public TextArea txtKeterangan;

    private TamuKegiatan undangan;
    private MainApp mainApp;
    private TamuDao tamuDao;
    private KegiatanDao kegiatanDao;
    private TamuKegiatanDao tamuKegiatanDao;

    private final ObservableList<String> listTamu = FXCollections.observableArrayList();
    private final ObservableList<String> listKegiatan = FXCollections.observableArrayList();

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;
    private String operator;

    public void setMainApp(MainApp mainApp, TamuKegiatan undangan, String operator) {
        this.mainApp = mainApp;
        this.undangan = undangan;
        this.operator = operator;

        if (undangan != null){
            setUndangan(undangan);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tamuDao = new TamuDao();
        kegiatanDao = new KegiatanDao();
        try {
            if (tamuDao.getConnection() && kegiatanDao.getConnection()){
                listTamu.removeAll();
                listKegiatan.removeAll();

                listTamu.addAll(tamuDao.getAllTamuName());
                listKegiatan.addAll(kegiatanDao.getAllKegiatanName());

                cbKegiatan.getItems().addAll(listKegiatan);
                cbTamu.getItems().addAll(listTamu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setUndangan(TamuKegiatan undangan) {
        cbTamu.setValue(undangan.getNama());
        cbKegiatan.setValue(undangan.getKegiatan());
        txtKeterangan.setText(undangan.getKeterangan());
    }

    @FXML
    public void clearForm() {
        cbTamu.getItems().clear();
        cbKegiatan.getItems().clear();
        txtKeterangan.clear();
    }

    @FXML
    public void gotoUndangan(){ mainApp.showUndangan(); }

    @FXML
    public void onUserSubmitted() {
        String namaform = cbTamu.getValue();
        String kegiatanform = cbKegiatan.getValue();
        String keterangan = txtKeterangan.getText();

        tamuDao = new TamuDao();
        kegiatanDao = new KegiatanDao();
        tamuKegiatanDao = new TamuKegiatanDao();

        TamuKegiatan model = new TamuKegiatan();
        if (tamuDao.getConnection() && kegiatanDao.getConnection()){
            try {
                if (tamuKegiatanDao.isUndanganExist(model.getIdKegiatan())){
                    tamuKegiatanDao.update(new String[]{model.getKeterangan(), model.getIdTamu(), model.getKegiatan(), model.getIdUndangan()});
                    alertInfo("Success", "Data telah diubah!");
                } else {
                    tamuKegiatanDao.save(kegiatanDao.getIdByName(kegiatanform), tamuDao.getIdByName(namaform), model.getKeterangan());
                    alertInfo("Success","Data telah ditambahkan!");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

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
