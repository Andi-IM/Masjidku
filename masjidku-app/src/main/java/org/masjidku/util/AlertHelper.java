package org.masjidku.util;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class AlertHelper {

    public static void alertError(Stage dialogStage, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Prompt");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void alertInfo(Stage dialogStage, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(dialogStage);
        alert.setTitle("Prompt");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static <T> void handleTableSelection(javafx.scene.control.TableView<T> table, javafx.scene.control.Button btnEdit, javafx.scene.control.Button btnRemove) {
        if (table.getSelectionModel().isEmpty()) {
            btnEdit.setDisable(true);
            btnRemove.setDisable(true);
        } else {
            btnEdit.setDisable(false);
            btnRemove.setDisable(false);
        }
    }
}
