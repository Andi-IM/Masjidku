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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.masjidku.MainApp;
import org.masjidku.model.kegiatan.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SecretaryUndanganForm implements Initializable {

    @FXML
    public ChoiceBox<Kegiatan> cbKegiatan;
    @FXML
    public ChoiceBox<Tamu> cbTamu;
    @FXML
    public TextArea txtKeterangan;

    private TamuKegiatan undangan;
    private MainApp mainApp;
    private TamuDao tamuDao;
    private KegiatanDao kegiatanDao;
    private TamuKegiatanDao tamuKegiatanDao;

    private final ObservableList<Tamu> listTamu = FXCollections.observableArrayList();
    private final ObservableList<Kegiatan> listKegiatan = FXCollections.observableArrayList();

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;
    private String operator;

    public void setMainApp(MainApp mainApp, TamuKegiatan undangan) {
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

                listTamu.addAll(tamuDao.getAll());
                listKegiatan.addAll(kegiatanDao.getAll());

                cbKegiatan.getItems().addAll(listKegiatan);
                cbTamu.getItems().addAll(listTamu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setUndangan(TamuKegiatan undangan) {

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

    }

    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }
}
