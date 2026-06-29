module org.masjidku.reporting {
    requires transitive org.masjidku.reporting.client;
    requires org.masjidku.common;
    requires net.sf.jasperreports.core;
    requires net.sf.jasperreports.pdf;
    requires org.slf4j;
    requires java.desktop;
    requires org.apache.commons.logging;
    requires org.apache.commons.collections4;
    requires java.sql;
    requires java.xml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.dataformat.xml;


    provides org.masjidku.reporting.client.service.ReportService
            with org.masjidku.reporting.service.impl.ReportServiceImpl;


}
