package org.masjidku.reporting.client.service;

import java.io.InputStream;
import java.util.Map;

public interface ReportService {
    void createReport(Map<String, Object> parameters, InputStream reportStream);
    void showReport();
    void exportToPdf(String destFilePath);
}
