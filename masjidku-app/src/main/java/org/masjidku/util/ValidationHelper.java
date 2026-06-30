package org.masjidku.util;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import net.synedra.validatorfx.Validator;

public class ValidationHelper {

    private ValidationHelper() {
        // Utility class — do not instantiate
    }

    public static void registerRequiredField(Validator validator, TextField field, String fieldKey, String errorMessage) {
        validator.createCheck()
                .dependsOn(fieldKey, field.textProperty())
                .withMethod(c -> {
                    String val = c.get(fieldKey);
                    if (val == null || val.isBlank()) {
                        c.error(errorMessage);
                    }
                })
                .decorates(field);
    }

    public static void registerNumericField(Validator validator, TextField field, String fieldKey, String requiredMessage, String numericMessage) {
        validator.createCheck()
                .dependsOn(fieldKey, field.textProperty())
                .withMethod(c -> {
                    String val = c.get(fieldKey);
                    if (val == null || val.isBlank()) {
                        c.error(requiredMessage);
                    } else if (!val.matches("\\d+")) {
                        c.error(numericMessage);
                    }
                })
                .decorates(field);
    }

    public static void registerDatePicker(Validator validator, DatePicker datePicker, String fieldKey, String errorMessage) {
        validator.createCheck()
                .dependsOn(fieldKey, datePicker.valueProperty())
                .withMethod(c -> {
                    if (c.get(fieldKey) == null) {
                        c.error(errorMessage);
                    }
                })
                .decorates(datePicker);
    }
}
