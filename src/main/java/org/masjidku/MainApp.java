
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
import org.masjidku.accountant.*;
import org.masjidku.admin.*;
import org.masjidku.controller.*;
import org.masjidku.model.kegiatan.Kegiatan;
import org.masjidku.model.kegiatan.Tamu;
import org.masjidku.model.kegiatan.TamuKegiatan;
import org.masjidku.model.session.Session;
import org.masjidku.model.session.UserSession;
import org.masjidku.model.user.User;
import org.masjidku.model.user.UserProfile;
import org.masjidku.principal.PrincipalHome;
import org.masjidku.principal.PrincipalRoot;
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
    public MainApp(){ }


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Masjidku");
        this.primaryStage.resizableProperty().setValue(Boolean.FALSE);
        // App icon
        this.primaryStage.getIcons()
                .add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("./icon/favicon.png"))));

        initRootLayout();
        showContent();
    }

    /**
     *  Initializes the root layout
     */
    public void initRootLayout(){
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
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     *  Show the content inside the root layout
     */
    public void showContent(){
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
    public void showLogin(){
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
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Show App About
     */
    public void showAbout(){
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

    public void recordSession(User user){
        this.user = user;
        session = new Session();
        session.getConnection();
        session.logUserSession(user.getUserId());
        userSession = session.getSessionData(user.getUserId());
    }

    public void showProfile(){
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
    public void setAdminView(){
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
        } catch (IOException e){
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
    public void showUser(){
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
    public void showUserEditScene(User user){
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
    public void setPrincipalView(){
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
        } catch (IOException e){
            System.err.println(e.getMessage());
            e.getCause();
        }
    }

    /**
     * Showing principal home
     */
    public void showPrincipalHome(){
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
        } catch (IOException e){
            System.err.println(e.getMessage());
            e.getCause();
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
    public void showKegiatan(){
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("secretary/secretary_kegiatan.fxml"));
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
    public void showKegiatanEditform(Kegiatan kegiatan){
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("secretary/secretary_kegiatan_form.fxml"));
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
    public void showTamu(){
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("secretary/secretary_tamu.fxml"));
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
     * @param tamu tamu
     */
    public void showTamuEditForm(Tamu tamu){
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("secretary/secretary_tamu_form.fxml"));
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
    public void showUndangan(){
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("secretary/secretary_undangan.fxml"));
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
     * @param undangan
     */
    public void showUndanganEditForm(TamuKegiatan undangan){
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("secretary/secretary_undangan_form.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            SecretaryUndanganForm controller = loader.getController();
            controller.setMainApp(this, undangan);
        } catch (IOException e) {
            System.err.println(e.getMessage());
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
        } catch (IOException e){
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
            loader.setLocation(getClass().getResource("accountant/accountant_anakYatim.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            AccountantAnakyatim controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
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
            System.err.println(e.getMessage());
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
            System.err.println(e.getMessage());
        }
    }

    /**
     * Logout
     */
    public void onLogoutAction() {
        try {
            session.logout();
            session.updateUserSession(userSession.getSession_id());
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

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage
     * @param args arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void showDonasiAYatim() {
    }

    public void showDaftarAnakYatim() {
    }
}