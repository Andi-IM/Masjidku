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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.masjidku.MainApp;
import org.masjidku.model.accounting.anakyatim.AnakYatim;

import java.net.URL;
import java.util.ResourceBundle;

public class PenerimaAnakYatim implements Initializable {

    @FXML
    private TableView<AnakYatim> tblAYKeluar;
    @FXML
    private TableColumn<String, String> nomor;
    @FXML
    private TableColumn<AnakYatim, String> donatur;
    @FXML
    private TableColumn<AnakYatim, String> jumlah;
    @FXML
    private TableColumn<AnakYatim, String> tanggal;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnRemove;

    private MainApp mainApp;

    // create some stage
    @SuppressWarnings("unused")
    private Stage dialogStage;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void onLogoutClick(){ mainApp.onLogoutAction(); }

    @FXML
    public void onMouseClicked(){
        if(tblAYKeluar.getSelectionModel().isEmpty()){
            btnEdit.setDisable(true);
            btnRemove.setDisable(true);
            btnReset.setDisable(true);
        } else {
            btnEdit.setDisable(false);
            btnRemove.setDisable(false);
            btnReset.setDisable(false);
        }
    }

    @FXML
    public void onCreateListener(){
        AnakYatim temp = new AnakYatim();
        mainApp.editAnakYatim(temp);
    }

    @FXML
    public void onEditListener(){
        AnakYatim selectedItem = tblAYKeluar.getSelectionModel().getSelectedItem();
        if (selectedItem != null){
            mainApp.editAnakYatim(selectedItem);
        } else {
            alertError("Null Error", "Data tidak ditemukan!");
        }
    }

    @FXML
    public void onRemoveListener(){

    }

    @FXML
    public void onResetListener(){

    }

    @FXML
    public void gotoHome() { mainApp.showAnakYatim(); }

    /**
     * Alert Error Builder
     * @param header header message
     * @param content content message
     */
    @SuppressWarnings("SameParameterValue")
    private void alertError(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Prompt");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }

    /**
     * Alert Info Builder
     * @param header header message
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
}
