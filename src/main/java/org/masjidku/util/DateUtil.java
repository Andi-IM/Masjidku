package org.masjidku.util;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date Parser to the specific format.
 *
 * @author Andi Irham
 */
public class DateUtil {
    static final String OLD_FORMAT = "dd-MMMMM-yyyy";
    static final String NEW_FORMAT = "yyyy-MM-dd";

    private boolean isValidDate(String input) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(NEW_FORMAT);
            formatter.parse(input);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public String dateFormatter(String getDate) {
        try {
            if (isValidDate(getDate)) {
                SimpleDateFormat formatter = new SimpleDateFormat(NEW_FORMAT);
                Date date = formatter.parse(getDate);
                formatter.applyPattern(OLD_FORMAT);
                return formatter.format(date);
            } else {
                SimpleDateFormat formatter = new SimpleDateFormat(OLD_FORMAT);
                Date date = formatter.parse(getDate);
                formatter.applyPattern(NEW_FORMAT);
                return formatter.format(date);
            }
        } catch (ParseException e) {
            // e.getMessage();
            JOptionPane.showMessageDialog(null,
                    "ERROR: " + e.getMessage());
        }
        return null;
    }
}
