module org.masjidku.reporting {
    requires transitive org.masjidku.reporting.client;
    requires java.sql;
    requires org.masjidku.common;
    requires jasperreports;
    requires com.github.librepdf.openpdf;
    requires java.desktop; // For JasperViewer (Swing)

    provides org.masjidku.reporting.client.service.ReportService 
        with org.masjidku.reporting.service.impl.ReportServiceImpl;
}
