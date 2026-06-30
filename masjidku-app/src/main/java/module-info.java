open module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires org.masjidku.common;
    requires com.google.common;
    requires org.slf4j;
    requires net.synedra.validatorfx;

    requires org.masjidku.accounting.client;
    requires org.masjidku.events.client;
    requires org.masjidku.reporting.client;
    requires org.masjidku.accounting;
    requires org.masjidku.events;
    requires dagger;
    requires javax.inject;
    requires org.hibernate.orm.core;

    uses org.masjidku.accounting.client.service.AccountingClient;

    uses org.masjidku.events.client.EventsClient;
    uses org.masjidku.events.client.model.Kegiatan;
    uses org.masjidku.reporting.client.service.ReportService;
}


