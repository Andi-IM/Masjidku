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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.masjidku.MainApp;
import org.masjidku.accounting.client.model.anakyatim.AnakYatim;
import org.masjidku.accounting.client.service.AnakYatimService;
import org.masjidku.util.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PenerimaAnakYatim extends org.masjidku.accountant.BaseTableController<AnakYatim> {
    private static final Logger log = LoggerFactory.getLogger(PenerimaAnakYatim.class);
    private final AnakYatimService dao = ServiceProvider.get(AnakYatimService.class);

    @FXML
    private TableView<AnakYatim> tableAnakyatim;
    @FXML
    private TableColumn<AnakYatim, String> nama;
    @FXML
    private TableColumn<AnakYatim, String> jumlah;
    @FXML
    private TableColumn<AnakYatim, String> usia;
    @FXML
    private TableColumn<AnakYatim, String> tanggal;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnRemove;

    private MainApp mainApp;


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


    @Override
    protected void setupTableColumns() {
        org.masjidku.util.AlertHelper.setupOutflowColumns(nama, jumlah, tanggal);
usia.setCellValueFactory(new PropertyValueFactory<>("usia"));
}

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }


    @FXML
    public void onCreateListener() {
        AnakYatim temp = new AnakYatim();
        mainApp.editAnakYatim(temp);
    }


    @FXML
    public void gotoHome() {
        mainApp.showAnakYatim();
    }


    @Override
    protected org.slf4j.Logger getLogger() {
        return log;
    }

    @Override
    protected TableView<AnakYatim> getTableView() {
        return tableAnakyatim;
    }

    @Override
    protected Button getBtnEdit() {
        return btnEdit;
    }

    @Override
    protected Button getBtnRemove() {
        return btnRemove;
    }

    @Override
    protected List<AnakYatim> fetchAllData() throws java.sql.SQLException {
        return dao.getAll();
    }

    @Override
    protected boolean checkIfExist(AnakYatim item) throws java.sql.SQLException {
        return dao.isAnakYatimExist(item.getId());
    }

    @Override
    protected void deleteItem(AnakYatim item) throws java.sql.SQLException {
        dao.delete(item.getId());
    }

    @Override
    protected void handleEdit(AnakYatim item) {
        mainApp.editAnakYatim(item);
    }

    @FXML
    public void onEditListener() {
        super.onEditAction();
    }
}
