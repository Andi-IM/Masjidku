package org.masjidku.accountant;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import net.synedra.validatorfx.Validator;
import org.masjidku.accounting.client.service.AccountingClient;
import org.masjidku.navigation.AppRouter;
import org.masjidku.util.ServiceProvider;

import static org.masjidku.di.DiProvider.getAppComponent;

public abstract class BaseEditController<T> {
    protected final AccountingClient client = ServiceProvider.get(AccountingClient.class);
    protected final Validator validator = new Validator();

    protected AppRouter mainApp;
    protected String operator;
    protected T model;

    @SuppressWarnings("unused")
    protected Stage dialogStage;


    @FXML
    protected javafx.scene.control.TextField txtNama;
    @FXML
    protected javafx.scene.control.TextField txtJumlah;
    @FXML
    protected javafx.scene.control.DatePicker date;

    @FXML
    public void initialize() {
        org.masjidku.util.ValidationHelper.registerRequiredField(validator, txtNama, "nama", "Nama/Tujuan harus diisi!");
        org.masjidku.util.ValidationHelper.registerNumericField(validator, txtJumlah, "jumlah", "Jumlah harus diisi!", "Jumlah harus berupa angka!");
        org.masjidku.util.ValidationHelper.registerDatePicker(validator, date, "tanggal", "Tanggal harus dipilih!");
        customInitialize();
    }

    protected void customInitialize() {}

    @FXML
    public void clearForm() {
        txtNama.clear();
        txtJumlah.clear();
        date.getEditor().clear();
        customClearForm();
    }

    protected void customClearForm() {}

    public void setMainApp(AppRouter mainApp, T model) {
        this.operator = getAppComponent().getSessionManager().getCurrentUsername();
        this.mainApp = mainApp;
        this.model = model;

        if (model != null && isModelExists(model)) {
            setModelData(model);
        }
    }

    protected abstract boolean isModelExists(T model);

    protected abstract void setModelData(T model);

    protected boolean formValidation() {
        return validator.validate();
    }

    @FXML
    public abstract void onSubmitted();

    @FXML
    public abstract void gotoList();

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }

}
