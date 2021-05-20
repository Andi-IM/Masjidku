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
