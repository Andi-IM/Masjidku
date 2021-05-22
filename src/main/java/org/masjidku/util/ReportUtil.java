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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("unused")
public class ReportUtil {
    final Connection connection;
    final DatabaseConnection db;

    public ReportUtil(){
        db = new DatabaseConnection();
        connection = db.getConnection();
    }

    public void previewReportMenu() {
        try {
            HashMap<String, Object> parameter = new HashMap<>();
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport("./report/Report.jasper", parameter, connection);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        }
    }
}
