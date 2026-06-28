module org.masjidku.reporting {
    requires transitive org.masjidku.reporting.client;
    requires java.sql;
    requires org.masjidku.common;
    requires net.sf.jasperreports.core;
    requires org.slf4j;
    requires java.desktop;

    provides org.masjidku.reporting.client.service.ReportService 
        with org.masjidku.reporting.service.impl.ReportServiceImpl;
}
