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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.masjidku.util.ServiceProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.masjidku.navigation.AppRouter;
import org.masjidku.events.application.usecase.KegiatanUseCase;
import org.masjidku.events.application.usecase.TamuUseCase;
import org.masjidku.events.client.repository.TamuRepository;
import org.masjidku.events.domain.entity.TamuKegiatan;
import org.masjidku.events.client.service.TamuKegiatanUseCase;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SecretaryUndanganForm implements Initializable {
    private static final Logger log = LoggerFactory.getLogger(SecretaryUndanganForm.class);

    @FXML
    public ChoiceBox<String>cbKegiatan;
    @FXML
    public ChoiceBox<String>cbTamu;
    @FXML
    public TextArea txtKeterangan;

    private AppRouter mainApp;
    private TamuUseCase TamuUseCase;
    private KegiatanUseCase KegiatanUseCase;

    private final ObservableList<String> listTamu = FXCollections.observableArrayList();
    private final ObservableList<String> listKegiatan = FXCollections.observableArrayList();

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;
    private String operator;

    public void setMainApp(AppRouter mainApp, TamuKegiatan undangan) {
        String operator = org.masjidku.model.session.SessionManager.getInstance().getCurrentUser().getUsername();
        this.mainApp = mainApp;
        this.operator = operator;

        if (undangan != null){
            setUndangan(undangan);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TamuUseCase = new TamuUseCase(org.masjidku.util.ServiceProvider.get(TamuRepository.class));
        KegiatanUseCase = ServiceProvider.get(KegiatanUseCase.class);
        try {
                listTamu.removeAll();
                listKegiatan.removeAll();

                listTamu.addAll(TamuUseCase.getAllTamuName());
                listKegiatan.addAll(KegiatanUseCase.getAllKegiatanName());

                cbKegiatan.getItems().addAll(listKegiatan);
                cbTamu.getItems().addAll(listTamu);
            

        } catch (SQLException e) {
            log.error("An error occurred", e);
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

        TamuUseCase = new TamuUseCase(org.masjidku.util.ServiceProvider.get(TamuRepository.class));
        KegiatanUseCase = ServiceProvider.get(KegiatanUseCase.class);
        TamuKegiatanUseCase tamuKegiatanUseCase = ServiceProvider.get(TamuKegiatanUseCase.class);

        TamuKegiatan model = new TamuKegiatan();
            try {
                if (tamuKegiatanUseCase.isUndanganExist(model.getIdKegiatan())){
                    tamuKegiatanUseCase.update(new String[]{model.getKeterangan(), model.getIdTamu(), model.getKegiatan(), model.getIdUndangan()});
                    org.masjidku.util.AlertHelper.alertInfo(dialogStage, "Success", "Data telah diubah!");
                } else {
                    tamuKegiatanUseCase.save(KegiatanUseCase.getIdByName(kegiatanform), TamuUseCase.getIdByName(namaform), model.getKeterangan(), operator);
                    org.masjidku.util.AlertHelper.alertInfo(dialogStage, "Success","Data telah ditambahkan!");
                }
            } catch (SQLException throwables) {
                log.error("An error occurred", throwables);
            }
        

    }

    @FXML
    public void onLogoutClick() { mainApp.onLogoutAction(); }


}




