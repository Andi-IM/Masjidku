package org.masjidku.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.masjidku.MainApp;
import org.masjidku.accountant.AccountantHome;
import org.masjidku.accountant.anakyatim.EditDonaturAnakYatim;
import org.masjidku.accountant.anakyatim.EditPenerimaAnakYatim;
import org.masjidku.accountant.operasional.EditDonaturOperasional;
import org.masjidku.accountant.operasional.EditPembayaranOperasional;
import org.masjidku.accountant.pembangunan.EditDonaturPembangunan;
import org.masjidku.accountant.pembangunan.EditPembayaranPembangunan;
import org.masjidku.accountant.tpa.EditDonaturTpa;
import org.masjidku.accountant.tpa.EditPembayaranTpa;
import org.masjidku.accountant.zakat.EditDonaturZakat;
import org.masjidku.accountant.zakat.EditPenerimaZakat;
import org.masjidku.accounting.client.model.anakyatim.AnakYatim;
import org.masjidku.accounting.client.model.anakyatim.DonasiAYatim;
import org.masjidku.accounting.client.model.operasional.DonasiOperasional;
import org.masjidku.accounting.client.model.operasional.Operasional;
import org.masjidku.accounting.client.model.pembangunan.DonasiPembangunan;
import org.masjidku.accounting.client.model.pembangunan.Pembangunan;
import org.masjidku.accounting.client.model.tpa.TpaKeluar;
import org.masjidku.accounting.client.model.tpa.TpaMasuk;
import org.masjidku.accounting.client.model.zakat.ZakatKeluar;
import org.masjidku.accounting.client.model.zakat.ZakatMasuk;
import org.masjidku.admin.AdminHome;
import org.masjidku.admin.UserForm;
import org.masjidku.controller.EditProfileController;
import org.masjidku.controller.ProfileController;
import org.masjidku.controller.RootLayoutController;
import org.masjidku.events.client.model.Kegiatan;
import org.masjidku.events.client.model.Tamu;
import org.masjidku.events.client.model.TamuKegiatan;
import org.masjidku.model.user.User;
import org.masjidku.model.user.UserProfile;
import org.masjidku.principal.PrincipalHome;
import org.masjidku.principal.PrincipalLaporan;
import org.masjidku.secretary.SecretaryHome;
import org.masjidku.secretary.SecretaryKegiatanForm;
import org.masjidku.secretary.SecretaryTamuForm;
import org.masjidku.secretary.SecretaryUndanganForm;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewManager {
    private static final Logger LOGGER = Logger.getLogger(ViewManager.class.getName());

    private final Stage primaryStage;
    private SplitPane rootLayout;
    private final MainApp mainApp;

    public ViewManager(Stage primaryStage, MainApp mainApp) {
        this.primaryStage = primaryStage;
        this.mainApp = mainApp;
    }

    public void injectMainApp(Object rawController) {
        if (rawController != null) {
            try {
                java.lang.reflect.Method method = rawController.getClass().getMethod("setMainApp", MainApp.class);
                method.invoke(rawController, mainApp);
            } catch (NoSuchMethodException e) {
                // Ignore
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "An error occurred", e);
            }
        }
    }

    public void setRootView(String fxmlPath, Runnable homeMethod) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(fxmlPath));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            injectMainApp(loader.getController());

            if (homeMethod != null) {
                homeMethod.run();
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Gagal memuat view: " + fxmlPath, e);
        }
    }

    public void loadHomeRoot() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("home_root.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            RootLayoutController controller = loader.getController();
            if (controller != null) {
                controller.setMainApp(mainApp);
                controller.btn_home.setSelected(true);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An error occurred", e);
        }
    }

    public void loadView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(fxmlPath));
            AnchorPane overview = loader.load();
            rootLayout.getItems().set(1, overview);

            injectMainApp(loader.getController());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Gagal memuat view: " + fxmlPath, e);
        }
    }

    public <T> T loadViewAndGetController(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(fxmlPath));
            AnchorPane overview = loader.load();
            rootLayout.getItems().set(1, overview);
            return loader.getController();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Gagal memuat view: " + fxmlPath, e);
            return null;
        }
    }

    public void showContent() {
        loadView("home.fxml");
    }

    public void showLogin() {
        loadView("login.fxml");
    }

    public void showAbout() {
        loadView("about.fxml");
    }

    public void showProfile() {
        ProfileController controller = loadViewAndGetController("profile.fxml");
        if (controller != null) {
            controller.setMainApp(mainApp);
        }
    }

    public void editProfile(UserProfile profile) {
        EditProfileController controller = loadViewAndGetController("edit_profile.fxml");
        if (controller != null) {
            controller.setMainApp(mainApp, profile);
        }
    }

    public void setAdminView() {
        setRootView("admin/admin_root.fxml", this::showAdminHome);
    }

    public void showAdminHome() {
        AdminHome controller = loadViewAndGetController("admin/admin_home.fxml");
        if (controller != null) {
            controller.setMainApp(mainApp);
        }
    }

    public void showUser() {
        loadView("admin/user_lists.fxml");
    }

    public void showUserEditScene(User user) {
        UserForm controller = loadViewAndGetController("admin/user_form.fxml");
        if (controller != null) {
            controller.setUser(user);
            controller.setMainApp(mainApp);
        }
    }

    public void showUserLog() {
        loadView("admin/user_logs.fxml");
    }

    public void setPrincipalView() {
        setRootView("principal/principal_root.fxml", this::showPrincipalHome);
    }

    public void showPrincipalHome() {
        PrincipalHome controller = loadViewAndGetController("principal/principal_home.fxml");
        if (controller != null) {
            controller.setMainApp(mainApp);
        }
    }

    public void showLaporan() {
        PrincipalLaporan controller = loadViewAndGetController("principal/principal_laporan.fxml");
        if (controller != null) {
            controller.setMainApp(mainApp);
        }
    }

    public void showData() {
        loadView("principal/principal_read_data.fxml");
    }

    public void showKegiatanOverview() {
        loadView("principal/report/kegiatan/report_kegiatan.fxml");
    }

    public void showKegiatanData() {
        loadView("principal/report/kegiatan/list_kegiatan.fxml");
    }

    public void showTamuData() {
        loadView("principal/report/kegiatan/list_tamu.fxml");
    }

    public void showUndanganData() {
        loadView("principal/report/kegiatan/list_undangan.fxml");
    }

    public void showAnakYatimData() {
        loadView("principal/report/keuangan/report_anakyatim.fxml");
    }

    public void showAnakYatimMasuk() {
        loadView("principal/report/keuangan/anakyatim/list_donatur_anakyatim.fxml");
    }

    public void showAnakYatimKeluar() {
        loadView("principal/report/keuangan/anakyatim/list_anakyatim.fxml");
    }

    public void showPembangunanData() {
        loadView("principal/report/keuangan/report_pembangunan.fxml");
    }

    public void showPembangunanMasuk() {
        loadView("principal/report/keuangan/pembangunan/list_donatur_pembangunan.fxml");
    }

    public void showPembangunanKeluar() {
        loadView("principal/report/keuangan/pembangunan/list_pembangunan.fxml");
    }

    public void showOperasionalData() {
        loadView("principal/report/keuangan/report_operasional.fxml");
    }

    public void showOperasionalMasuk() {
        loadView("principal/report/keuangan/operasional/list_donatur_operasional.fxml");
    }

    public void showOperasionalKeluar() {
        loadView("principal/report/keuangan/operasional/list_operasional.fxml");
    }

    public void showZakatData() {
        loadView("principal/report/keuangan/report_zakat.fxml");
    }

    public void showZakatMasuk() {
        loadView("principal/report/keuangan/zakat/list_donatur_zakat.fxml");
    }

    public void showZakatKeluar() {
        loadView("principal/report/keuangan/zakat/list_zakat.fxml");
    }

    public void showTpaData() {
        loadView("principal/report/keuangan/report_tpa.fxml");
    }

    public void showTpaMasuk() {
        loadView("principal/report/keuangan/tpa/list_donatur_tpa.fxml");
    }

    public void showTpaKeluar() {
        loadView("principal/report/keuangan/tpa/list_tpa.fxml");
    }

    public void setSecretaryView() {
        setRootView("secretary/secretary_root.fxml", this::setSecretaryHome);
    }

    public void setSecretaryHome() {
        SecretaryHome controller = loadViewAndGetController("secretary/secretary_home.fxml");
        if (controller != null) {
            controller.setMainApp(mainApp);
        }
    }

    public void showKegiatan() {
        loadView("secretary/list_kegiatan.fxml");
    }

    public void showKegiatanEditform(Kegiatan kegiatan) {
        SecretaryKegiatanForm controller = loadViewAndGetController("secretary/form_kegiatan.fxml");
        if (controller != null) {
            controller.setMainApp(mainApp, kegiatan);
        }
    }

    public void showTamu() {
        loadView("secretary/list_tamu.fxml");
    }

    public void showTamuEditForm(Tamu tamu) {
        SecretaryTamuForm controller = loadViewAndGetController("secretary/form_tamu.fxml");
        if (controller != null) {
            controller.setMainApp(mainApp, tamu);
        }
    }

    public void showUndangan() {
        loadView("secretary/list_undangan.fxml");
    }

    public void showUndanganEditForm(TamuKegiatan undangan) {
        SecretaryUndanganForm controller = loadViewAndGetController("secretary/form_undangan.fxml");
        if (controller != null) {
            controller.setMainApp(mainApp, undangan);
        }
    }

    public void setAccountantView() {
        setRootView("accountant/accountant_root.fxml", this::setAccountantHome);
    }

    public void setAccountantHome() {
        AccountantHome controller = loadViewAndGetController("accountant/accountant_home.fxml");
        if (controller != null) {
            controller.setMainApp(mainApp);
        }
    }

    public void showAnakYatim() {
        loadView("accountant/accountant_anakyatim.fxml");
    }

    public void showDonasiAYatim() {
        loadView("accountant/anakyatim/list_donatur_anakyatim.fxml");
    }

    public void showDaftarAnakYatim() {
        loadView("accountant/anakyatim/list_anakyatim.fxml");
    }

    public void editDonaturAnakYatim(DonasiAYatim model) {
        EditDonaturAnakYatim controller = loadViewAndGetController("accountant/anakyatim/form_donatur_anakyatim.fxml");
        if (controller != null) {
            controller.setMainApp(mainApp, model);
        }
    }

    public void editAnakYatim(AnakYatim model) {
        EditPenerimaAnakYatim controller = loadViewAndGetController("accountant/anakyatim/form_anakyatim.fxml");
        if (controller != null) {
            controller.setMainApp(mainApp, model);
        }
    }

    public void showZakat() {
        loadView("accountant/accountant_zakat.fxml");
    }

    public void showDonaturZakat() {
        loadView("accountant/zakat/list_donatur_zakat.fxml");
    }

    public void showDaftarPenerimaZakat() {
        loadView("accountant/zakat/list_zakat.fxml");
    }

    public void editDonaturZakat(ZakatMasuk model) {
        EditDonaturZakat controller = loadViewAndGetController("accountant/zakat/form_donatur_zakat.fxml");
        if (controller != null) {
            controller.setMainApp(mainApp, model);
        }
    }

    public void editPenerimaZakat(ZakatKeluar model) {
        EditPenerimaZakat controller = loadViewAndGetController("accountant/zakat/form_zakat.fxml");
        if (controller != null) {
            controller.setMainApp(mainApp, model);
        }
    }

    public void showPembangunan() {
        loadView("accountant/accountant_pembangunan.fxml");
    }

    public void showDonaturPembangunan() {
        loadView("accountant/pembangunan/list_donatur_pembangunan.fxml");
    }

    public void showAlokasiPembangunan() {
        loadView("accountant/pembangunan/list_pembangunan.fxml");
    }

    public void editDonaturPembangunan(DonasiPembangunan model) {
        EditDonaturPembangunan controller = loadViewAndGetController("accountant/pembangunan/form_donatur_pembangunan.fxml");
        if (controller != null) {
            controller.setMainApp(mainApp, model);
        }
    }

    public void editAlokasiPembangunan(Pembangunan model) {
        EditPembayaranPembangunan controller = loadViewAndGetController("accountant/pembangunan/form_pembangunan.fxml");
        if (controller != null) {
            controller.setMainApp(mainApp, model);
        }
    }

    public void showOperasional() {
        loadView("accountant/accountant_operasional.fxml");
    }

    public void showDonaturOperasional() {
        loadView("accountant/operasional/list_donatur_operasional.fxml");
    }

    public void showAlokasiOperasional() {
        loadView("accountant/operasional/list_operasional.fxml");
    }

    public void editDonaturOperasional(DonasiOperasional model) {
        EditDonaturOperasional controller = loadViewAndGetController("accountant/operasional/form_donatur_operasional.fxml");
        if (controller != null) {
            controller.setMainApp(mainApp, model);
        }
    }

    public void editAlokasiOperasional(Operasional model) {
        EditPembayaranOperasional controller = loadViewAndGetController("accountant/operasional/form_operasional.fxml");
        if (controller != null) {
            controller.setMainApp(mainApp, model);
        }
    }

    public void showTpa() {
        loadView("accountant/accountant_tpa.fxml");
    }

    public void showDonaturTpa() {
        loadView("accountant/tpa/list_donatur_tpa.fxml");
    }

    public void showAlokasiTpa() {
        loadView("accountant/tpa/list_tpa.fxml");
    }

    public void editDonaturTpa(TpaMasuk model) {
        EditDonaturTpa controller = loadViewAndGetController("accountant/tpa/form_donatur_tpa.fxml");
        if (controller != null) {
            controller.setMainApp(mainApp, model);
        }
    }

    public void editAlokasiTpa(TpaKeluar model) {
        EditPembayaranTpa controller = loadViewAndGetController("accountant/tpa/form_tpa.fxml");
        if (controller != null) {
            controller.setMainApp(mainApp, model);
        }
    }
}
