package org.masjidku.util;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class AlertHelper {
    public static void alertInfo(Stage owner, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (owner != null) {
            alert.initOwner(owner);
        }
        alert.setTitle("Prompt");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void alertError(Stage owner, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (owner != null) {
            alert.initOwner(owner);
        }
        alert.setTitle("Prompt");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
