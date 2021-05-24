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
