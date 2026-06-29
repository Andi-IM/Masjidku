package org.masjidku.util;

import javafx.scene.control.ToggleGroup;

public class UIUtils {

    private UIUtils() {
        // Utility class — do not instantiate
    }

    public static void preventEmptyToggleSelection(ToggleGroup groupButton) {
        if (groupButton != null) {
            if (!groupButton.getToggles().isEmpty()) {
                groupButton.getToggles().getFirst().setSelected(true);
            }
            groupButton.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal == null && oldVal != null) {
                    javafx.application.Platform.runLater(() -> oldVal.setSelected(true));
                }
            });
        }
    }
}
