package org.masjidku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
       Parent root = null;
       try {
           root = FXMLLoader.load(getClass().getResource("main.fxml"));
       } catch (IOException e){
           throw new RuntimeException(e);
       }

        Label label = new Label("Hello World!");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
