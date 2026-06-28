package org.masjidku.reporting.service.impl;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.masjidku.reporting.client.service.ReportService;
import org.masjidku.util.db.DatabaseConnection;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

public class ReportServiceImpl implements ReportService {
    private JasperReport jreport;
    private JasperViewer jviewer;
    private JasperPrint jprint;

    @Override
    public void createReport(Map<String, Object> parameters, InputStream reportStream) {
        DatabaseConnection db = new DatabaseConnection();
        Connection connect = db.getConnection();
        if (connect != null) {
            try {
                jreport = (JasperReport) JRLoader.loadObject(reportStream);
                jprint = JasperFillManager.fillReport(jreport, parameters, connect);
            } catch (JRException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void showReport() {
        if (jprint != null) {
            jviewer = new JasperViewer(jprint, false); // false = don't exit JVM on close
            jviewer.setVisible(true);
        }
    }
}
