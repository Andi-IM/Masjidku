
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

package org.masjidku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
import org.masjidku.model.session.SessionManager;
import org.masjidku.model.user.User;
import org.masjidku.model.user.UserProfile;
import org.masjidku.principal.PrincipalHome;
import org.masjidku.secretary.SecretaryHome;
import org.masjidku.secretary.SecretaryKegiatanForm;
import org.masjidku.secretary.SecretaryTamuForm;
import org.masjidku.secretary.SecretaryUndanganForm;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainApp extends Application {
    private static final Logger LOGGER = Logger.getLogger(MainApp.class.getName());

    private Stage primaryStage;
    private SplitPane rootLayout;

    /**
     * Constructor
     */
    public MainApp() {
    }

    private void injectMainApp(Object rawController) {
        if (rawController != null) {
            try {
                java.lang.reflect.Method method = rawController.getClass().getMethod("setMainApp", MainApp.class);
                method.invoke(rawController, this);
            } catch (NoSuchMethodException e) {
                // Ignore
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "An error occurred", e);
            }
        }
    }


    private void setRootView(String fxmlPath, Runnable homeMethod) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlPath));
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


    private void loadHomeRoot() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("home_root.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            RootLayoutController controller = loader.getController();
            if (controller != null) {
                controller.setMainApp(this);
                controller.btn_home.setSelected(true);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An error occurred", e);
        }
    }

    private void loadView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlPath));
            AnchorPane overview = loader.load();
            rootLayout.getItems().set(1, overview);

            injectMainApp(loader.getController());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Gagal memuat view: " + fxmlPath, e);
        }
    }

    private <T> T loadViewAndGetController(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlPath));
            AnchorPane overview = loader.load();
            rootLayout.getItems().set(1, overview);
            return loader.getController();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Gagal memuat view: " + fxmlPath, e);
            return null;
        }
    }


    @Override
    public void start(Stage primaryStage) {
        // Initialize SQLite Database schema if necessary
        org.masjidku.util.db.SQLiteInitService.initializeDatabase();

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Masjidku");
        this.primaryStage.resizableProperty().setValue(Boolean.FALSE);
        // App icon
        this.primaryStage.getIcons()
                .add(new Image("file:src/main/resources/org/masjidku/icon/favicon.png"));

        initRootLayout();
        showContent();
    }

    @Override
    public void stop() throws Exception {
        SessionManager.getInstance().logout();
        super.stop();
    }

    /**
     * Initializes the root layout
     */
    public void initRootLayout() {
        loadHomeRoot();
        if (primaryStage != null) {
            primaryStage.show();
        }
    }

    /**
     * Show the content inside the root layout
     */
    public void showContent() {
        loadView("home.fxml");
    }

    /**
     * Show User Login
     */
    public void showLogin() {
        loadView("login.fxml");
    }

    /**
     * Show App About
     */
    public void showAbout() {
        loadView("about.fxml");
    }

    /**
     * record user sessions
     *
     * @param user user
     */
    public void recordSession(User user) {
        SessionManager.getInstance().login(user);
    }

    /**
     * show user's profile
     */
    public void showProfile() {
        ProfileController controller = loadViewAndGetController("profile.fxml");
        if (controller != null) {
            controller.setMainApp(this);
        }
    }

    /**
     * User profile edit
     *
     * @param profile the user profile
     */
    public void editProfile(UserProfile profile) {
        EditProfileController controller = loadViewAndGetController("edit_profile.fxml");
        if (controller != null) {
            controller.setMainApp(this, profile);
        }
    }


    /**
     * Admin Privilege
     */
    public void setAdminView() {
        setRootView("admin/admin_root.fxml", this::showAdminHome);
    }

    /**
     * Admin Home
     */
    public void showAdminHome() {
        AdminHome controller = loadViewAndGetController("admin/admin_home.fxml");
        if (controller != null) {
            controller.setMainApp(this);
        }
    }

    /**
     * show list of user
     */
    public void showUser() {
        loadView("admin/user_lists.fxml");
    }

    /**
     * Open the scene to edit detail for the specified user. If the user
     * clicks OK, the changes are saved to the database
     *
     * @param user the user object to be edited.
     */
    public void showUserEditScene(User user) {
        UserForm controller = loadViewAndGetController("admin/user_form.fxml");
        if (controller != null) {
            controller.setUser(user);
            controller.setMainApp(this);
        }
    }

    /**
     * Show list of User Activities
     */
    public void showUserLog() {
        loadView("admin/user_logs.fxml");
    }

    /**
     * Principal Privilege
     */
    public void setPrincipalView() {
        setRootView("principal/principal_root.fxml", this::showPrincipalHome);
    }

    /**
     * Showing principal home
     */
    public void showPrincipalHome() {
        PrincipalHome controller = loadViewAndGetController("principal/principal_home.fxml");
        if (controller != null) {
            controller.setMainApp(this);
        }
    }

    /**
     * Show laporan
     */
    public void showLaporan() {
        loadView("principal/principal_under_dev.fxml");
    }

    public void showKegiatanReport() {

    }

    public void showAnakYatimReport() {

    }

    public void showPembangunanReport() {

    }

    public void showOperasionalReport() {

    }

    public void showZakatReport() {

    }

    public void showTpaReport() {

    }

    /**
     * Show data
     */
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

    /**
     * Keuangan Data
     */
    // Anak Yatim
    public void showAnakYatimData() {
        loadView("principal/report/keuangan/report_anakyatim.fxml");
    }

    public void showAnakYatimMasuk() {
        loadView("principal/report/keuangan/anakyatim/list_donatur_anakyatim.fxml");
    }

    public void showAnakYatimKeluar() {
        loadView("principal/report/keuangan/anakyatim/list_anakyatim.fxml");
    }

    // Pembangunan
    public void showPembangunanData() {
        loadView("principal/report/keuangan/report_pembangunan.fxml");
    }

    public void showPembangunanMasuk() {
        loadView("principal/report/keuangan/pembangunan/list_donatur_pembangunan.fxml");
    }

    public void showPembangunanKeluar() {
        loadView("principal/report/keuangan/pembangunan/list_pembangunan.fxml");
    }

    // Operasional
    public void showOperasionalData() {
        loadView("principal/report/keuangan/report_operasional.fxml");
    }

    public void showOperasionalMasuk() {
        loadView("principal/report/keuangan/operasional/list_donatur_operasional.fxml");
    }

    public void showOperasionalKeluar() {
        loadView("principal/report/keuangan/operasional/list_operasional.fxml");
    }

    // Zakat
    public void showZakatData() {
        loadView("principal/report/keuangan/report_zakat.fxml");
    }

    public void showZakatMasuk() {
        loadView("principal/report/keuangan/zakat/list_donatur_zakat.fxml");
    }

    public void showZakatKeluar() {
        loadView("principal/report/keuangan/zakat/list_zakat.fxml");
    }

    // TPA
    public void showTpaData() {
        loadView("principal/report/keuangan/report_tpa.fxml");
    }

    public void showTpaMasuk() {
        loadView("principal/report/keuangan/tpa/list_donatur_tpa.fxml");
    }

    public void showTpaKeluar() {
        loadView("principal/report/keuangan/tpa/list_tpa.fxml");
    }

    /**
     * Secretary Privilege
     */
    public void setSecretaryView() {
        setRootView("secretary/secretary_root.fxml", this::setSecretaryHome);
    }

    /**
     * Showing secretary home
     */
    public void setSecretaryHome() {
        SecretaryHome controller = loadViewAndGetController("secretary/secretary_home.fxml");
        if (controller != null) {
            controller.setMainApp(this);
        }
    }

    /**
     * Showing secretary kegiatan
     */
    public void showKegiatan() {
        loadView("secretary/list_kegiatan.fxml");
    }

    /**
     * Showing secretary edit kegiatan
     */
    public void showKegiatanEditform(Kegiatan kegiatan) {
        SecretaryKegiatanForm controller = loadViewAndGetController("secretary/form_kegiatan.fxml");
        if (controller != null) {
            controller.setMainApp(this, kegiatan);
        }
    }

    /**
     * Showing secretary tamu
     */
    public void showTamu() {
        loadView("secretary/list_tamu.fxml");
    }

    /**
     * Showing secretary tamu edit
     *
     * @param tamu tamu
     */
    public void showTamuEditForm(Tamu tamu) {
        SecretaryTamuForm controller = loadViewAndGetController("secretary/form_tamu.fxml");
        if (controller != null) {
            controller.setMainApp(this, tamu);
        }
    }

    /**
     * showing undangan
     */
    public void showUndangan() {
        loadView("secretary/list_undangan.fxml");
    }

    /**
     * Showing undangan edit form
     *
     * @param undangan undangan
     */
    public void showUndanganEditForm(TamuKegiatan undangan) {
        SecretaryUndanganForm controller = loadViewAndGetController("secretary/form_undangan.fxml");
        if (controller != null) {
            controller.setMainApp(this, undangan);
        }
    }

    /**
     * Accountant Privilege
     */
    public void setAccountantView() {
        setRootView("accountant/accountant_root.fxml", this::setAccountantHome);
    }

    /**
     * show accountant home
     */
    public void setAccountantHome() {
        AccountantHome controller = loadViewAndGetController("accountant/accountant_home.fxml");
        if (controller != null) {
            controller.setMainApp(this);
        }
    }

    /**
     * show anakYatimprompt
     */
    public void showAnakYatim() {
        loadView("accountant/accountant_anakyatim.fxml");
    }

    public void showDonasiAYatim() {
        loadView("accountant/anakyatim/list_donatur_anakYatim.fxml");
    }

    public void showDaftarAnakYatim() {
        loadView("accountant/anakyatim/list_anakYatim.fxml");
    }

    public void editDonaturAnakYatim(DonasiAYatim model) {
        EditDonaturAnakYatim controller = loadViewAndGetController("accountant/anakyatim/form_donatur_anakyatim.fxml");
        if (controller != null) {
            controller.setMainApp(this, model);
        }
    }

    public void editAnakYatim(AnakYatim model) {
        EditPenerimaAnakYatim controller = loadViewAndGetController("accountant/anakyatim/form_anakyatim.fxml");
        if (controller != null) {
            controller.setMainApp(this, model);
        }
    }

    /**
     * show zakatPrompt
     */
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
            controller.setMainApp(this, model);
        }
    }

    public void editPenerimaZakat(ZakatKeluar model) {
        EditPenerimaZakat controller = loadViewAndGetController("accountant/zakat/form_zakat.fxml");
        if (controller != null) {
            controller.setMainApp(this, model);
        }
    }

    /**
     * show Pembangunan
     */
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
            controller.setMainApp(this, model);
        }
    }

    public void editAlokasiPembangunan(Pembangunan model) {
        EditPembayaranPembangunan controller = loadViewAndGetController("accountant/pembangunan/form_pembangunan.fxml");
        if (controller != null) {
            controller.setMainApp(this, model);
        }
    }

    /**
     * show operational
     */
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
            controller.setMainApp(this, model);
        }
    }

    public void editAlokasiOperasional(Operasional model) {
        EditPembayaranOperasional controller = loadViewAndGetController("accountant/operasional/form_operasional.fxml");
        if (controller != null) {
            controller.setMainApp(this, model);
        }
    }

    /**
     * show tpa
     */
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
            controller.setMainApp(this, model);
        }
    }

    public void editAlokasiTpa(TpaKeluar model) {
        EditPembayaranTpa controller = loadViewAndGetController("accountant/tpa/form_tpa.fxml");
        if (controller != null) {
            controller.setMainApp(this, model);
        }
    }

    /**
     * Logout
     */
    public void onLogoutAction() {
        org.masjidku.model.session.SessionManager.getInstance().logout();

        loadHomeRoot();
        showContent();
    }

    /**
     * Returns the main stage
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
