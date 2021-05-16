package org.masjidku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.masjidku.admin.AdminHome;
import org.masjidku.admin.AdminRoot;
import org.masjidku.controller.*;
import org.masjidku.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class MainApp extends Application {

    protected Stage primaryStage;
    protected SplitPane rootLayout;

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
                    getClass().getResource("root_layout.fxml"));
            rootLayout = loader.load();

            // show the scene containing the root layout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the MainApp
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

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

            // Give the controller access to the main app.
            AboutController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAdminView(User user){
        try {
            // load root layout from fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                    getClass().getResource("admin/root_layout.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the MainApp
            AdminRoot controller = loader.getController();
            controller.setMainApp(this);

            showAdminHome(user);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void showAdminHome(User user) {
        try {
            // Load Content
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("admin/home.fxml"));
            AnchorPane overview = loader.load();

            // set the item into the right divider.
            rootLayout.getItems().set(1, overview);

            // Give the controller access to the main app.
            AdminHome controller = loader.getController();
            controller.setMainApp(this, user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onLogoutAction() {
        try {
            // load root layout from fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                    getClass().getResource("root_layout.fxml"));
            rootLayout = loader.load();

            // show the scene containing the root layout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the MainApp
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            showContent();
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public void dismiss(){
        primaryStage.close();
    }

    /**
     * Returns the main stage
     * @param args arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
