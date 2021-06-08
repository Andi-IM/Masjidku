package org.masjidku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.masjidku.accountant.AccountantHome;
import org.masjidku.accountant.AccountantRoot;
import org.masjidku.admin.AdminHome;
import org.masjidku.admin.AdminRoot;
import org.masjidku.admin.UserForm;
import org.masjidku.admin.UserLists;
import org.masjidku.controller.*;
import org.masjidku.model.user.User;
import org.masjidku.principal.PrincipalHome;
import org.masjidku.principal.PrincipalRoot;
import org.masjidku.secretary.SecretaryHome;
import org.masjidku.secretary.SecretaryRoot;
import org.masjidku.model.session.Session;

import java.io.IOException;
import java.util.Objects;

public class MainApp extends Application {

    private Stage primaryStage;
    private SplitPane rootLayout;

    private Session session;

    /**
     * Constructor
     */
    public MainApp(){ }


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Masjidku");

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

    /**
     * Admin Privilege
     * @param user model
     */
    public void setAdminView(User user){
        try {
            session = new Session();
            session.getConnection();
            session.logUserSession(user.getUserId());
            // load root layout from fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                    getClass().getResource("admin/admin_root.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the MainApp
            AdminRoot controller = loader.getController();
            controller.setMainApp(this, user);
            showAdminHome(user);
        } catch (IOException e){
            System.err.println(e.getMessage());
            e.getCause();
        }
    }

    /**
     * Admin Home
     * @param user model
     */
    private void showAdminHome(User user) {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("admin/admin_home.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            AdminHome controller = loader.getController();
            controller.setMainApp(this, user);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void showUser(String user_id){
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("admin/user_lists.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            UserLists controller = loader.getController();
            controller.setMainApp(this, user_id);
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
            controller.setMainApp(this, user.getUserId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Principal Privilege
     * @param user principal user model
     */
    public void setPrincipalView(User user){
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
            controller.setMainApp(this, user);

            // set initialize home
            showPrincipalHome(user);
        } catch (IOException e){
            System.err.println(e.getMessage());
            e.getCause();
        }
    }

    /**
     * Showing principal home
     * @param user model
     */
    public void showPrincipalHome(User user){
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("principal/principal_home.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            PrincipalHome controller = loader.getController();
            controller.setMainApp(this, user);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Secretary Privilege
     * @param user secretary username
     */
    public void setSecretaryView(User user) {
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
            controller.setMainApp(this, user);

            // set initialize home
            setSecretaryHome(user);
        } catch (IOException e){
            System.err.println(e.getMessage());
            e.getCause();
        }
    }

    /**
     * Showing secretary home
     * @param user model
     */
    public void setSecretaryHome(User user) {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("secretary/secretary_home.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            SecretaryHome controller = loader.getController();
            controller.setMainApp(this, user);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Accountant Privilege
     * @param user Accountant username
     */
    public void setAccountantView(User user) {
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
            controller.setMainApp(this, user);

            // set initialize home
            setAccountantHome(user);
        } catch (IOException e){
            System.err.println(e.getMessage());
            e.getCause();
        }
    }

    /**
     * show accountant home
     * @param user model
     */
    public void setAccountantHome(User user) {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accountant/accountant_home.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            AccountantHome controller = loader.getController();
            controller.setMainApp(this, user);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    /**
     * Logout
     * @param userid user id
     */
    public void onLogoutAction(String userid) {
        try {
            session.logout();
            session.updateUserSession(userid);

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
}
