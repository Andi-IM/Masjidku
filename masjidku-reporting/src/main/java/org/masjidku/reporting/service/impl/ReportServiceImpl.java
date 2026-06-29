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
    public void showReport(String reportPath) {
        showReport(reportPath, new java.util.HashMap<>());
    }

    @Override
    public void showReport(String reportPath, Map<String, Object> parameters) {
        InputStream reportStream = getClass().getResourceAsStream(reportPath);
        if (reportStream == null) {
            log.error("Report template could not be found for path: {}", reportPath);
            return;
        }
        createReport(parameters, reportStream);
        showReport();
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

    @Override
    public void exportToPdf(String reportPath, String destFilePath) {
        exportToPdf(reportPath, new java.util.HashMap<>(), destFilePath);
    }

    @Override
    public void exportToPdf(String reportPath, Map<String, Object> parameters, String destFilePath) {
        InputStream reportStream = getClass().getResourceAsStream(reportPath);
        if (reportStream == null) {
            log.error("Report template could not be found for path: {}", reportPath);
            return;
        }
        createReport(parameters, reportStream);
        exportToPdf(destFilePath);
    }
}
