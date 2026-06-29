package org.masjidku.reporting.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.view.JasperViewer;

import org.masjidku.reporting.client.service.ReportService;
import org.masjidku.util.db.DatabaseConnection;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

public class ReportServiceImpl implements ReportService {
    private static final Logger log = LoggerFactory.getLogger(ReportServiceImpl.class);
    private JasperPrint jprint;

    @Override
    public void createReport(Map<String, Object> parameters, InputStream reportStream) {
        DatabaseConnection db = new DatabaseConnection();
        Connection connect = db.getConnection();
        if (connect != null) {
            try {
                JasperReport jreport = JasperCompileManager.compileReport(reportStream);
                jprint = JasperFillManager.fillReport(jreport, parameters, connect);
            } catch (JRException e) {
                log.error("An error occurred", e);
            }
        }
    }

    @Override
    public void showReport() {
        if (jprint != null) {
            JasperViewer jviewer = new JasperViewer(jprint, false); // false = don't exit JVM on close
            jviewer.setVisible(true);
        }
    }
    @Override
    public void exportToPdf(String destFilePath) {
        if (jprint != null) {
            try {
                JasperExportManager.exportReportToPdfFile(jprint, destFilePath);
            } catch (JRException e) {
                log.error("An error occurred", e);
            }
        }
    }
}
