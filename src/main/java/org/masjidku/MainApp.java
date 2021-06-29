
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

import  javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.masjidku.accountant.*;
import org.masjidku.accountant.anakyatim.*;
import org.masjidku.accountant.operasional.*;
import org.masjidku.accountant.pembangunan.*;
import org.masjidku.accountant.tpa.*;
import org.masjidku.accountant.zakat.*;
import org.masjidku.admin.*;
import org.masjidku.controller.*;
import org.masjidku.model.accounting.anakyatim.*;
import org.masjidku.model.accounting.operasional.*;
import org.masjidku.model.accounting.pembangunan.*;
import org.masjidku.model.accounting.tpa.*;
import org.masjidku.model.accounting.zakat.*;
import org.masjidku.model.kegiatan.*;
import org.masjidku.model.session.*;
import org.masjidku.model.user.*;
import org.masjidku.principal.*;
import org.masjidku.principal.report.kegiatan.*;
import org.masjidku.principal.report.keuangan.*;
import org.masjidku.principal.report.keuangan.anakyatim.*;
import org.masjidku.principal.report.keuangan.operasional.*;
import org.masjidku.principal.report.keuangan.pembangunan.*;
import org.masjidku.principal.report.keuangan.tpa.*;
import org.masjidku.principal.report.keuangan.zakat.*;
import org.masjidku.secretary.*;

import java.io.IOException;
import java.util.Objects;

public class MainApp extends Application {

    private Stage primaryStage;
    private SplitPane rootLayout;

    private Session session;
    private UserSession userSession;
    private User user;

    /**
     * Constructor
     */
    public MainApp() {
    }


    @Override
    public void start(Stage primaryStage) {
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
        if (session != null) {
            session.logout();
            session.updateUserSession(userSession.getSession_id());
        }
        super.stop();
    }

    /**
     * Initializes the root layout
     */
    public void initRootLayout() {
        try {
            // load root layout from fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                    getClass().getResource("home_root.fxml"));
            rootLayout = loader.load();

            // show the scene containing the root layout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the MainApp
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
            controller.btn_home.setSelected(true);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show the content inside the root layout
     */
    public void showContent() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("home.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            HomeController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show User Login
     */
    public void showLogin() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("login.fxml"));
            AnchorPane overview = loader.load();


            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            LoginController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show App About
     */
    public void showAbout() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("about.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * record user sessions
     *
     * @param user user
     */
    public void recordSession(User user) {
        this.user = user;
        session = new Session();
        session.getConnection();
        session.logUserSession(user.getUserId());
        userSession = session.getSessionData(user.getUserId());
    }

    /**
     * show user's profile
     */
    public void showProfile() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("profile.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            ProfileController controller = loader.getController();
            controller.setMainApp(this, user.getUserId());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * User profile edit
     *
     * @param profile the user profile
     */
    public void editProfile(UserProfile profile) {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("edit_profile.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            EditProfileController controller = loader.getController();
            controller.setMainApp(this, profile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Admin Privilege
     */
    public void setAdminView() {
        try {
            // load root layout from fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                    getClass().getResource("admin/admin_root.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the MainApp
            AdminRoot controller = loader.getController();
            controller.setMainApp(this);
            showAdminHome();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.getCause();
        }
    }

    /**
     * Admin Home
     */
    private void showAdminHome() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("admin/admin_home.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            AdminHome controller = loader.getController();
            controller.setMainApp(this, user.getUsername());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * show list of user
     */
    public void showUser() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("admin/user_lists.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            UserLists controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Open the scene to edit detail for the specified user. If the user
     * clicks OK, the changes are save to the database
     *
     * @param user the user object to be edited.
     */
    public void showUserEditScene(User user) {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("admin/user_form.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            UserForm controller = loader.getController();
            controller.setUser(user);
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show list of User Activities
     */
    public void showUserLog() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("admin/user_logs.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            UserLogs controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Principal Privilege
     */
    public void setPrincipalView() {
        try {
            // load root layout from fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                    getClass().getResource("principal/principal_root.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the MainApp
            PrincipalRoot controller = loader.getController();
            controller.setMainApp(this);

            // set initialize home
            showPrincipalHome();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.getCause();
        }
    }

    /**
     * Showing principal home
     */
    public void showPrincipalHome() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("principal/principal_home.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            PrincipalHome controller = loader.getController();
            controller.setMainApp(this, user.getUsername());

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Show laporan
     */
    public void showLaporan() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("principal/principal_laporan.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            PrincipalLaporan controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
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
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("principal/principal_read_data.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            PrincipalReadData controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void showKegiatanOverview() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("principal/report/kegiatan/report_kegiatan.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            KegiatanOverview controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void showKegiatanData() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("principal/report/kegiatan/list_kegiatan.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            ListKegiatan controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showTamuData() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("principal/report/kegiatan/list_tamu.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            ListTamu controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void showUndanganData() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("principal/report/kegiatan/list_undangan.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            ListUndangan controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Keuangan Data
     */
    // Anak Yatim
    public void showAnakYatimData() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("principal/report/keuangan/report_anakyatim.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            AnakYatimReport controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void showAnakYatimMasuk() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("principal/report/keuangan/anakyatim/list_donatur_anakyatim.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            DataDonaturAnakYatim controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void showAnakYatimKeluar() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("principal/report/keuangan/anakyatim/list_anakyatim.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            DataPenerimaAnakYatim controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    // Pembangunan
    public void showPembangunanData() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("principal/report/keuangan/report_pembangunan.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            PembangunanReport controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void showPembangunanMasuk() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("principal/report/keuangan/pembangunan/list_donatur_pembangunan.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            DataDonaturPembangunan controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void showPembangunanKeluar() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("principal/report/keuangan/pembangunan/list_pembangunan.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            DataPembayaranPembangunan controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    // Operasional
    public void showOperasionalData() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("principal/report/keuangan/report_operasional.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            OperasionalReport controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void showOperasionalMasuk() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("principal/report/keuangan/operasional/list_donatur_operasional.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            DataDonaturOperasional controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void showOperasionalKeluar() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("principal/report/keuangan/operasional/list_operasional.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            DataPembayaranOperasional controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    // Zakat
    public void showZakatData() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("principal/report/keuangan/report_zakat.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            ZakatReport controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void showZakatMasuk() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("principal/report/keuangan/zakat/list_donatur_zakat.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            DataDonaturZakat controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void showZakatKeluar() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("principal/report/keuangan/zakat/list_zakat.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            DataPenerimaZakat controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    // TPA
    public void showTpaData() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("principal/report/keuangan/report_tpa.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            TpaReport controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void showTpaMasuk() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("principal/report/keuangan/tpa/list_donatur_tpa.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            DataDonaturTpa controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void showTpaKeluar() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("principal/report/keuangan/tpa/list_tpa.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            DataPembayaranTpa controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Secretary Privilege
     */
    public void setSecretaryView() {
        try {
            // load root layout from fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                    getClass().getResource("secretary/secretary_root.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the MainApp
            SecretaryRoot controller = loader.getController();
            controller.setMainApp(this);

            // set initialize home
            setSecretaryHome();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Showing secretary home
     */
    public void setSecretaryHome() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("secretary/secretary_home.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            SecretaryHome controller = loader.getController();
            controller.setMainApp(this, user.getUsername());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Showing secretary kegiatan
     */
    public void showKegiatan() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("secretary/list_kegiatan.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            SecretaryKegiatan controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Showing secretary edit kegiatan
     */
    public void showKegiatanEditform(Kegiatan kegiatan) {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("secretary/form_kegiatan.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            SecretaryKegiatanForm controller = loader.getController();
            controller.setMainApp(this, kegiatan, user.getUserId());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Showing secretary tamu
     */
    public void showTamu() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("secretary/list_tamu.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            SecretaryTamu controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Showing secretary tamu edit
     *
     * @param tamu tamu
     */
    public void showTamuEditForm(Tamu tamu) {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("secretary/form_tamu.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            SecretaryTamuForm controller = loader.getController();
            controller.setMainApp(this, tamu, user.getUserId());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * showing undangan
     */
    public void showUndangan() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("secretary/list_undangan.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            SecretaryUndangan controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Showing undangan edit form
     *
     * @param undangan undangan
     */
    public void showUndanganEditForm(TamuKegiatan undangan) {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("secretary/form_undangan.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            SecretaryUndanganForm controller = loader.getController();
            controller.setMainApp(this, undangan, user.getUserId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Accountant Privilege
     */
    public void setAccountantView() {
        try {
            // load root layout from fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                    getClass().getResource("accountant/accountant_root.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the MainApp
            AccountantRoot controller = loader.getController();
            controller.setMainApp(this);

            // set initialize home
            setAccountantHome();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.getCause();
        }
    }

    /**
     * show accountant home
     */
    public void setAccountantHome() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accountant/accountant_home.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            AccountantHome controller = loader.getController();
            controller.setMainApp(this, user.getUsername());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * show anakYatimprompt
     */
    public void showAnakYatim() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accountant/accountant_anakyatim.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            AccountantAnakyatim controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.getCause();
        }
    }

    public void showDonasiAYatim() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accountant/anakyatim/list_donatur_anakYatim.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            DonaturAnakYatim controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void showDaftarAnakYatim() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accountant/anakyatim/list_anakYatim.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            PenerimaAnakYatim controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void editDonaturAnakYatim(DonasiAYatim model) {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accountant/anakyatim/form_donatur_anakyatim.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            EditDonaturAnakYatim controller = loader.getController();
            controller.setMainApp(this, model, user.getUsername());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editAnakYatim(AnakYatim model) {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accountant/anakyatim/form_anakyatim.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            EditPenerimaAnakYatim controller = loader.getController();
            controller.setMainApp(this, model, user.getUsername());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * show zakatPrompt
     */
    public void showZakat() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accountant/accountant_zakat.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            AccountantZakat controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDonaturZakat() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accountant/zakat/list_donatur_zakat.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            DonaturZakat controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void showDaftarPenerimaZakat() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accountant/zakat/list_zakat.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            PenerimaZakat controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void editDonaturZakat(ZakatMasuk model) {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accountant/zakat/form_donatur_zakat.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            EditDonaturZakat controller = loader.getController();
            controller.setMainApp(this, model, user.getUsername());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.getCause();
        }
    }

    public void editPenerimaZakat(ZakatKeluar model) {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accountant/zakat/form_zakat.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            EditPenerimaZakat controller = loader.getController();
            controller.setMainApp(this, model, user.getUsername());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.getCause();
        }
    }

    /**
     * show Pembangunan
     */
    public void showPembangunan() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accountant/accountant_pembangunan.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            AccountantPembangunan controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void showDonaturPembangunan() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accountant/pembangunan/list_donatur_pembangunan.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            DonaturPembangunan controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void showAlokasiPembangunan() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accountant/pembangunan/list_pembangunan.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            PembayaranPembangunan controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void editDonaturPembangunan(DonasiPembangunan model) {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accountant/pembangunan/form_donatur_pembangunan.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            EditDonaturPembangunan controller = loader.getController();
            controller.setMainApp(this, model, user.getUsername());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.getCause();
        }
    }

    public void editAlokasiPembangunan(Pembangunan model) {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accountant/pembangunan/form_pembangunan.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            EditPembayaranPembangunan controller = loader.getController();
            controller.setMainApp(this, model, user.getUsername());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.getCause();
        }
    }

    /**
     * show operational
     */
    public void showOperasional() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accountant/accountant_operasional.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            AccountantOperasional controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void showDonaturOperasional() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accountant/operasional/list_donatur_operasional.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            DonaturOperasional controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void showAlokasiOperasional() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accountant/operasional/list_operasional.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            PembayaranOperasional controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void editDonaturOperasional(DonasiOperasional model) {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accountant/operasional/form_donatur_operasional.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            EditDonaturOperasional controller = loader.getController();
            controller.setMainApp(this, model, user.getUsername());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.getCause();
        }
    }

    public void editAlokasiOperasional(Operasional model) {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accountant/operasional/form_operasional.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            EditPembayaranOperasional controller = loader.getController();
            controller.setMainApp(this, model, user.getUsername());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.getCause();
        }
    }

    /**
     * show tpa
     */
    public void showTpa() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accountant/accountant_tpa.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            AccountantTpa controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDonaturTpa() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accountant/tpa/list_donatur_tpa.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            DonaturTpa controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void showAlokasiTpa() {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accountant/tpa/list_tpa.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            PembayaranTpa controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void editDonaturTpa(TpaMasuk model) {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accountant/tpa/form_donatur_tpa.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            EditDonaturTpa controller = loader.getController();
            controller.setMainApp(this, model, user.getUsername());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.getCause();
        }
    }

    public void editAlokasiTpa(TpaKeluar model) {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accountant/tpa/form_tpa.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            EditPembayaranTpa controller = loader.getController();
            controller.setMainApp(this, model, user.getUsername());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.getCause();
        }
    }

    /**
     * Logout
     */
    public void onLogoutAction() {
        try {
            session.logout();
            session.updateUserSession(userSession.getSession_id());
            session = null;
            user = null;

            // load root layout from fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("home_root.fxml"));
            rootLayout = loader.load();

            // show the scene containing the root layout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the MainApp
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
            controller.btn_home.setSelected(true);
            showContent();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        launch(MainApp.class);
    }
}