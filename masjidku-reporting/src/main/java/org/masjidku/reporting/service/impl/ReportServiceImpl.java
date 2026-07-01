package org.masjidku.reporting.service.impl;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import org.masjidku.reporting.client.service.ReportConnectionProvider;
import org.masjidku.reporting.client.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Map;

public class ReportServiceImpl implements ReportService {
    private static final Logger log = LoggerFactory.getLogger(ReportServiceImpl.class);
    private JasperPrint jprint;

    private ReportConnectionProvider connectionProvider;

    @Override
    public void setConnectionProvider(ReportConnectionProvider provider) {
        this.connectionProvider = provider;
    }

    @Override
    public void createReport(Map<String, Object> parameters, InputStream reportStream) {
        if (connectionProvider == null) {
            log.error("ConnectionProvider is not set! Report cannot be generated.");
            return;
        }

        connectionProvider.executeWithConnection(connect -> {
            try {
                JasperReport jreport = JasperCompileManager.compileReport(reportStream);
                jprint = JasperFillManager.fillReport(jreport, parameters, connect);
            } catch (JRException e) {
                log.error("An error occurred while compiling or filling the report", e);
            }
        });
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
            String classLoaderPath = reportPath.startsWith("/") ? reportPath.substring(1) : reportPath;
            reportStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(classLoaderPath);
        }
        if (reportStream == null) {
            log.error("Failed to show report. Template could not be found for path: {}", reportPath);
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
                log.error("An error occurred while exporting the report to PDF", e);
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
            String classLoaderPath = reportPath.startsWith("/") ? reportPath.substring(1) : reportPath;
            reportStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(classLoaderPath);
        }
        if (reportStream == null) {
            log.error("Failed to export PDF. Template could not be found for path: {}", reportPath);
            return;
        }
        createReport(parameters, reportStream);
        exportToPdf(destFilePath);
    }
}
