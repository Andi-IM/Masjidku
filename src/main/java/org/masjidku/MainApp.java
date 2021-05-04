package org.masjidku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
       Parent root = null;
       try {
           root = FXMLLoader.load(getClass().getResource("main.fxml"));
       } catch (IOException e){
           throw new RuntimeException(e);
       }

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(MainApp.class.getResourceAsStream("./icon/favicon.png")));
        primaryStage.setTitle("Masjidku");
        primaryStage.show();
    }
}
