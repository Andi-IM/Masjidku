package org.masjidku.reporting.client.service;

import java.io.InputStream;
import java.util.Map;

public interface ReportService {
    void createReport(Map<String, Object> parameters, InputStream reportStream);
    void showReport();
    void showReport(String reportPath);
    void showReport(String reportPath, Map<String, Object> parameters);
    void exportToPdf(String destFilePath);
    void exportToPdf(String reportPath, String destFilePath);
    void exportToPdf(String reportPath, Map<String, Object> parameters, String destFilePath);
    void setConnectionProvider(ReportConnectionProvider provider);
}
